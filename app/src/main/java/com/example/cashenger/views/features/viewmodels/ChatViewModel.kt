package com.example.cashenger.views.features.viewmodels

import androidx.lifecycle.*
import com.example.cashenger.domain.chat.models.CommandSpecificResponse
import com.example.cashenger.domain.chat.models.MessageBody
import com.example.cashenger.domain.chat.models.ReplyMessageModel
import com.example.cashenger.domain.chat.models.SelfMessageModel
import com.example.cashenger.utils.FeaturesResources
import com.example.cashenger.utils.FeaturesResources.fieldIncompleteMessage
import com.example.cashenger.utils.FeaturesResources.incorrectExpenseOrIncomeMessage
import com.example.cashenger.utils.FeaturesResources.invalidAmountMessage
import com.example.cashenger.utils.FieldIncompleteException
import com.example.cashenger.utils.IncorrectIncomeExpenseException
import com.example.cashenger.utils.Injector
import com.example.cashenger.views.features.chat.CommandCenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.NumberFormatException

class ChatViewModel(
    private val commandCenter: CommandCenter
) : ViewModel() {

    private val _chatMsgs =
        MutableLiveData<List<MessageBody>>(listOf(FeaturesResources.getInitialWelcomeMessage()))
    val chatMsgs: LiveData<List<MessageBody>> get() = _chatMsgs

    fun sendMessage(msgModel: SelfMessageModel) = viewModelScope.launch {
        postNewReplies(msgBody = msgModel)
        delay(200)
        processReplyForTheMessage(msgModel)
    }

    private fun processReplyForTheMessage(msgModel: SelfMessageModel) = viewModelScope.launch {
        when (val response = commandCenter.verifyUserMessage(msgModel.msgText)) {
            is CommandSpecificResponse.NoCommandResponse -> {
                postNewReplies(ReplyMessageModel(FeaturesResources.replyMessageForError()))
                postNewReplies(ReplyMessageModel(FeaturesResources.possibleCommandsMessage()))
            }
            is CommandSpecificResponse.AddExpenseCommandResponse -> {
                handleAddExpenseDetails(response)
            }
            is CommandSpecificResponse.ShowAllResponse -> {
                postNewReplies(ReplyMessageModel("showed"))
            }
            is CommandSpecificResponse.ShowAllIncomes -> {
                postNewReplies(ReplyMessageModel("shows inc"))
            }
            is CommandSpecificResponse.ShowAllExpenses -> {
                postNewReplies(ReplyMessageModel("show exp"))
            }
        }
    }

    private fun postNewReplies(msgBody: MessageBody) {
        val currentConversation =
            _chatMsgs.value ?: listOf(FeaturesResources.getInitialWelcomeMessage())
        _chatMsgs.value = currentConversation.toMutableList().apply {
            add(msgBody)
        }
    }

    private suspend fun handleAddExpenseDetails(
        responseDetails: CommandSpecificResponse.AddExpenseCommandResponse
    ) = withContext(Dispatchers.Main) {
        try {
            val amount = responseDetails.amount
            val expenseTitle = responseDetails.expenseTitle
            val expenseOrIncome = responseDetails.expenseOrIncome
            if (amount.isNullOrEmpty() || expenseTitle.isNullOrEmpty() || expenseOrIncome.isNullOrEmpty()) {
                throw FieldIncompleteException()
            }
            val amountInLong = amount.toLong()
            val isExpense = when {
                expenseOrIncome.equals("expense", true) -> true
                expenseOrIncome.equals("income", true) -> false
                else -> throw IncorrectIncomeExpenseException()
            }

            postNewReplies(ReplyMessageModel("ok done"))

        } catch (e: NumberFormatException) {
            postNewReplies(ReplyMessageModel(invalidAmountMessage()))
        } catch (e: FieldIncompleteException) {
            postNewReplies(ReplyMessageModel(msgText = fieldIncompleteMessage()))
        } catch (e: IncorrectIncomeExpenseException) {
            postNewReplies(ReplyMessageModel(incorrectExpenseOrIncomeMessage()))
        }
    }

    companion object {
        class ChatVmFactory : ViewModelProvider.NewInstanceFactory() {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return ChatViewModel(Injector.getInjector().providesCommandCenter()) as T
            }
        }

    }
}
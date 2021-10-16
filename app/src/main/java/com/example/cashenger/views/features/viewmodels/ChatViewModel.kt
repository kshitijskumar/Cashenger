package com.example.cashenger.views.features.viewmodels

import androidx.lifecycle.*
import com.example.cashenger.domain.chat.models.MessageBody
import com.example.cashenger.domain.chat.models.SelfMessageModel
import com.example.cashenger.utils.FeaturesResources
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {

    private val _chatMsgs =
        MutableLiveData<List<MessageBody>>(listOf(FeaturesResources.getInitialWelcomeMessage()))
    val chatMsgs: LiveData<List<MessageBody>> get() = _chatMsgs

    fun sendMessage(msgModel: SelfMessageModel) = viewModelScope.launch {
        val currentConversation =
            _chatMsgs.value ?: listOf(FeaturesResources.getInitialWelcomeMessage())
        _chatMsgs.postValue(
            currentConversation.toMutableList().apply {
                add(msgModel)
            }
        )
    }
}
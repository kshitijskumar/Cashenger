package com.example.cashenger.views.features.chat

import com.example.cashenger.domain.chat.models.CommandSpecificResponse
import com.example.cashenger.utils.Commands
import java.util.*

class CommandCenter {

    fun verifyUserMessage(userMsg: String) : CommandSpecificResponse {
        val processedMsg = processUserMsgToRawForm(userMsg)
        return when {
            Commands.AddExpenseCommand.regexPattern.matcher(processedMsg).matches() -> {
                onAddExpenseCommandReceived(userMsg = userMsg)
            }
            else -> {
                CommandSpecificResponse.NoCommandResponse
            }
        }
    }

    private fun processUserMsgToRawForm(userMsg: String) : String {
        return userMsg.replace(" ", "").lowercase(Locale.getDefault())
    }

    private fun onAddExpenseCommandReceived(userMsg: String) : CommandSpecificResponse.AddExpenseCommandResponse {
        var amount: String? = null
        var expenseTitle: String? = null
        var expenseOrIncome: String? = null

        val stack = Stack<Int>()

        for (i in userMsg.indices) {
            val currChar = userMsg[i]
            if (currChar == '(') {
                stack.push(i)
            } else if (currChar == ')') {
                val substring = userMsg.substring(stack.pop()+1, i)
                when {
                    amount == null -> {
                        amount = substring
                    }
                    expenseTitle == null -> {
                        expenseTitle = substring
                    }
                    expenseOrIncome == null -> {
                        expenseOrIncome = substring
                    }
                }
            }
        }

        return CommandSpecificResponse.AddExpenseCommandResponse(
            amount = amount, expenseTitle = expenseTitle, expenseOrIncome = expenseOrIncome
        )
    }

}

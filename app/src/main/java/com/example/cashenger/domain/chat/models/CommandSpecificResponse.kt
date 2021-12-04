package com.example.cashenger.domain.chat.models


sealed class CommandSpecificResponse {

    object NoCommandResponse : CommandSpecificResponse()

    data class AddExpenseCommandResponse(
        val amount: String? = null,
        val expenseTitle: String? = null,
        val expenseOrIncome: String? = null
    ) : CommandSpecificResponse()

    object ShowAllResponse : CommandSpecificResponse()
    object ShowAllExpenses : CommandSpecificResponse()
    object ShowAllIncomes : CommandSpecificResponse()
    object ShowAllCmds : CommandSpecificResponse()
}
package com.example.cashenger.utils

import com.example.cashenger.domain.chat.models.ReplyMessageModel
import java.text.SimpleDateFormat
import java.util.*

object FeaturesResources {

    val supportedCategoriesList = listOf<ExpenseCategory>(
        ExpenseCategory.Other,
        ExpenseCategory.Food,
        ExpenseCategory.Entertainment,
        ExpenseCategory.Gift,
        ExpenseCategory.Petrol,
        ExpenseCategory.Education,
        ExpenseCategory.Salary,
        ExpenseCategory.Travel
    )

    fun getInitialWelcomeMessage() = ReplyMessageModel(
        msgText = "Hey there! What can I do for you?",
        timeStamp = getCurrentTimestamp()
    )

    fun getCurrentTimestamp() : String = Date(System.currentTimeMillis()).toTimestamp("hh:mm, dd/MM")

    fun Date.toTimestamp(timestampFormat: String) : String {
        val formatter = SimpleDateFormat(timestampFormat, Locale.getDefault())
        return formatter.format(this)
    }

    fun replyMessageForError() = "Sorry I am not sure how to process this request."

    fun possibleCommandsMessage() : String {
        return """These are some possible commands 
            
            |1. show all cmds : Shows all possible commands 
            |2. add (amount) for (title) in (expense/income) : Adds the expense details 
            |3. show all : Shows all expenses and incomes 
            |4. show expenses : Shows all expenses 
            |5. show incomes : Shows all incomes""".trimMargin()
    }

    fun fieldIncompleteMessage() = "Please make sure you mention all the details in your message (amount, title and expense/income)"

    fun invalidAmountMessage() = "Please enter a valid amount."

    fun incorrectExpenseOrIncomeMessage() = "Not sure whether to put it in expense or income. Please clarify again"

    fun addCommandHandledMsg(amount: String, reason: String, intoSection: String) = "$amount added for $reason  into $intoSection."

    fun getCommandsSuggestions() = listOf<String>(
        "add () for () in ()",
        "show all",
        "show incomes",
        "show expenses"
    )
}
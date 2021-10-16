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

}
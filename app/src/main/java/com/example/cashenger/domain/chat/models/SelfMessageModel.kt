package com.example.cashenger.domain.chat.models

import com.example.cashenger.utils.ExpenseCategory

data class SelfMessageModel(
    val msgText: String = "add (200) for (scooty petrol) in (expense)",
    val timeStamp: String = "22:12",
    val category: ExpenseCategory = ExpenseCategory.Food
) : MessageBody {
    override val isSelf: Boolean
        get() = true
}

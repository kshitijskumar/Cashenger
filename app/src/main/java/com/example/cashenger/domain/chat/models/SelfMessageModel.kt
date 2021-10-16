package com.example.cashenger.domain.chat.models

import com.example.cashenger.utils.ExpenseCategory
import com.example.cashenger.utils.FeaturesResources

data class SelfMessageModel(
    val msgText: String = "",
    val timeStamp: String = FeaturesResources.getCurrentTimestamp(),
    val category: ExpenseCategory = ExpenseCategory.Other
) : MessageBody {
    override val isSelf: Boolean
        get() = true
}

package com.example.cashenger.domain.records.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cashenger.utils.ExpenseCategory

@Entity(tableName = "income_expense_table")
data class IncomeExpenseModel(
    var title: String = "Some black money xD",
    var date: String = "",
    var amount: Long = 0,
    var isExpense: Boolean = true,
    var expenseCategory: Int = ExpenseCategory.Other.categoryId
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}

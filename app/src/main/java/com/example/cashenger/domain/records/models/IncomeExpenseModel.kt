package com.example.cashenger.domain.records.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cashenger.utils.ExpenseCategory

@Entity(tableName = "income_expense_table")
data class IncomeExpenseModel(
    var title: String = "Some black money xD",
    var date: String = "00:00 01/01/50",
    var amount: Long = 0,
    var isExpense: Boolean = true,
    var expenseCategory: Int = ExpenseCategory.Other.categoryId
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}

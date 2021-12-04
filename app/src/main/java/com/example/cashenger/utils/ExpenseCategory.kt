package com.example.cashenger.utils

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.example.cashenger.R
import com.example.cashenger.ui.theme.*

sealed class ExpenseCategory(val categoryId: Int, @DrawableRes val categoryIconRes: Int? = null, val bgColor: Color) {
    object Other : ExpenseCategory(categoryId = -1, categoryIconRes = R.drawable.ic_cancel, Grey)
    object Food : ExpenseCategory(categoryId = 0, categoryIconRes = R.drawable.ic_food, Color.Yellow)
    object Entertainment : ExpenseCategory(categoryId = 1, categoryIconRes = R.drawable.ic_entertainment, Color.Black)
    object Gift : ExpenseCategory(categoryId = 2, categoryIconRes = R.drawable.ic_gift, Orange)
    object Petrol : ExpenseCategory(categoryId = 3, categoryIconRes = R.drawable.ic_petrol, Color.Red)
    object Salary : ExpenseCategory(categoryId = 4, categoryIconRes = R.drawable.ic_money_sack, Green)
    object Travel : ExpenseCategory(categoryId = 5, categoryIconRes = R.drawable.ic_travel, Blue)
    object Education : ExpenseCategory(categoryId = 6, categoryIconRes = R.drawable.ic_education, Magenta)
}

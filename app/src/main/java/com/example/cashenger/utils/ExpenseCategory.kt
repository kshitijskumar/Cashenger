package com.example.cashenger.utils

import androidx.annotation.DrawableRes
import com.example.cashenger.R

sealed class ExpenseCategory(val categoryId: Int, @DrawableRes val categoryIconRes: Int? = null) {
    object Other : ExpenseCategory(categoryId = -1, categoryIconRes = 0)
    object Food : ExpenseCategory(categoryId = 0, categoryIconRes = R.drawable.ic_food)
}

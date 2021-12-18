package com.example.cashenger.views.features.records

import androidx.navigation.NavHostController
import com.example.cashenger.views.features.screens.Screens

data class FromRecordsScreenToDestination(
    val fromRecordsToEdit: (transactionId: Int) -> Unit = {}
)

fun getRecordsScreenNavigation(navHostController: NavHostController): FromRecordsScreenToDestination {
    return FromRecordsScreenToDestination(
        fromRecordsToEdit = {
            navHostController.navigate(Screens.EditTransactionScreen.route.replace("{transactionId}", it.toString()))
        }
    )
}
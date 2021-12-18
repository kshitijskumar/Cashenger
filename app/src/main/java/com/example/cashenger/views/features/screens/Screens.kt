package com.example.cashenger.views.features.screens

sealed class Screens(val route: String) {
    object ChatScreen : Screens("chat")
    object ListScreen : Screens("list/{navigateFor}")
    object EditTransactionScreen : Screens("edit/{transactionId}")
}

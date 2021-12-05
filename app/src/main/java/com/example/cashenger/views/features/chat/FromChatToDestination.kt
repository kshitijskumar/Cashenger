package com.example.cashenger.views.features.chat

data class FromChatToDestination(
    val navigateToRecords: (recordsFor: String) -> Unit = {} //expense/income/all
)

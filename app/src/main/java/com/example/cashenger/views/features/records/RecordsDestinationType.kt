package com.example.cashenger.views.features.records

sealed class RecordsDestinationType {
    object Stay: RecordsDestinationType()
    object AllTransactions: RecordsDestinationType()
    object Expense: RecordsDestinationType()
    object Income: RecordsDestinationType()
}

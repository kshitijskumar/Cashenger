package com.example.cashenger.views.features.records

sealed class RecordsScreenState {
    object FetchingTransactions : RecordsScreenState()
    object NoTransactionsFound : RecordsScreenState()
    object ShowTransactions : RecordsScreenState()
}

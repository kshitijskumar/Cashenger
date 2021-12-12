package com.example.cashenger.views.features.records

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cashenger.domain.records.models.IncomeExpenseModel
import com.example.cashenger.ui.theme.LightGrey
import com.example.cashenger.views.components.NoTransactionsText
import com.example.cashenger.views.components.RecordsAppbarComponent
import com.example.cashenger.views.components.TransactionItemComponent
import com.example.cashenger.views.features.viewmodels.RecordsViewModel

@Composable
fun RecordsListScreen(
    navigatingFor: String = "all",
    recordsVm: RecordsViewModel = viewModel(modelClass = RecordsViewModel::class.java, factory = RecordsViewModel.Companion.RecordViewModelFactory()),
) {

    Log.d("RecordsStuff", "compose")

    val transactionsList = recordsVm.transactionsList.observeAsState(initial = listOf())
    val uiState = recordsVm.uiState.observeAsState()
    var shouldShowDeleteDialog by remember { mutableStateOf(false) }
    var transactionItem by remember { mutableStateOf<IncomeExpenseModel?>(null) }

    Scaffold(
        topBar = {
            RecordsAppbarComponent(navigatingFor = navigatingFor)
        },
        backgroundColor = LightGrey,
        modifier = Modifier.fillMaxSize()
    ) {
        when(uiState.value) {
            is RecordsScreenState.FetchingTransactions -> {
                recordsVm.getTransactionsListForFollowing(navigatingFor)
            }
            is RecordsScreenState.NoTransactionsFound -> {
                NoTransactionsText()
            }
            is RecordsScreenState.ShowTransactions -> {
                LazyColumn {
                    items(items = transactionsList.value) { item ->
                        TransactionItemComponent(
                            transaction = item,
                            onLongClick = {
                                shouldShowDeleteDialog = true
                                transactionItem = it
                            },
                            onItemClick = {

                            }
                        )
                    }
                }
            }
        }

        if (shouldShowDeleteDialog) {
            val dismissCallback: () -> Unit = {
                shouldShowDeleteDialog = false
                transactionItem = null
            }
            transactionItem?.let { transaction ->
                RecordDeleteDialog(
                    transaction = transaction,
                    onDismissRequest = dismissCallback,
                    onDeleteClick = {
                        //delete
                        recordsVm.deleteTransaction(transaction)
                        dismissCallback.invoke()
                    },
                    onEditClick = {
                        //edit
                        dismissCallback.invoke()
                    }
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun RecordsListScreenPreview() {
    RecordsListScreen()
}
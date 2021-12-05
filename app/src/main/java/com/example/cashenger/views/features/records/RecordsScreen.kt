package com.example.cashenger.views.features.records

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cashenger.ui.theme.LightBlue
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
    recordsVm.getTransactionsListForFollowing(navigatingFor)
    val transactionsList = recordsVm.transactionsList.observeAsState(initial = listOf())
    
    Scaffold(
        topBar = {
            RecordsAppbarComponent(navigatingFor = navigatingFor)
        },
        backgroundColor = LightGrey,
        modifier = Modifier.fillMaxSize()
    ) {
        if (transactionsList.value.isNullOrEmpty()) {
            NoTransactionsText()
        } else {
            LazyColumn {
                items(items = transactionsList.value) { item ->
                    TransactionItemComponent(
                        transaction = item
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun RecordsListScreenPreview() {
    RecordsListScreen()
}
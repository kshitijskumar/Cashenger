package com.example.cashenger.views.features.chat

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cashenger.R
import com.example.cashenger.domain.chat.models.ReplyMessageModel
import com.example.cashenger.domain.chat.models.SelfMessageModel
import com.example.cashenger.ui.theme.Grey
import com.example.cashenger.views.components.MessageFieldComponent
import com.example.cashenger.views.components.ReplyMessageComponent
import com.example.cashenger.views.components.SelfMessageComponent
import com.example.cashenger.views.features.records.RecordsDestinationType
import com.example.cashenger.views.features.viewmodels.ChatViewModel

@ExperimentalFoundationApi
@Composable
fun ChatScreen(
    fromChatToDestination: FromChatToDestination = FromChatToDestination(),
    chatVM: ChatViewModel = viewModel(factory = ChatViewModel.Companion.ChatVmFactory())
) {
    val msgsListState = chatVM.chatMsgs.observeAsState(initial = listOf())
    val isResponseTyping = chatVM.isResponseTyping.observeAsState(initial = false)

    chatVM.navigateToRecordsCallback = {
        when(it) {
            is RecordsDestinationType.AllTransactions -> {
                fromChatToDestination.navigateToRecords.invoke("all")
            }
            is RecordsDestinationType.Income -> {
                fromChatToDestination.navigateToRecords.invoke("income")
            }
            is RecordsDestinationType.Expense -> {
                fromChatToDestination.navigateToRecords.invoke(("expense"))
            }
            else -> Unit
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = {
            Column(modifier = Modifier.fillMaxSize()) {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    reverseLayout = true
                ) {
                    items(items = msgsListState.value.reversed()) { message ->
                        if (message is SelfMessageModel) {
                            SelfMessageComponent(
                                msgBody = message
                            )
                        } else if (message is ReplyMessageModel) {
                            ReplyMessageComponent(
                                msgBody = message
                            )
                        }
                    }
                }
                if (isResponseTyping.value) {
                    Text(
                        text = stringResource(id = R.string.typing___),
                        color = Grey,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Light,
                        modifier = Modifier.padding(10.dp, 0.dp)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                MessageFieldComponent(
                    onSendClick = {
                        chatVM.sendMessage(it)
                    }
                )
            }
        }
    )
}

@ExperimentalFoundationApi
@Preview(showSystemUi = true)
@Composable
fun ChatScreenPreview() {
    ChatScreen()
}
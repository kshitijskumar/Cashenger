package com.example.cashenger.views.features.chat

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cashenger.domain.chat.models.ReplyMessageModel
import com.example.cashenger.domain.chat.models.SelfMessageModel
import com.example.cashenger.views.components.MessageFieldComponent
import com.example.cashenger.views.components.ReplyMessageComponent
import com.example.cashenger.views.components.SelfMessageComponent
import com.example.cashenger.views.features.viewmodels.ChatViewModel

@ExperimentalFoundationApi
@Composable
fun ChatScreen(
    chatVM: ChatViewModel = viewModel(factory = ChatViewModel.Companion.ChatVmFactory())
) {
    val msgsListState = chatVM.chatMsgs.observeAsState(initial = listOf())

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = {
            Column(modifier = Modifier.fillMaxSize()) {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    reverseLayout = true
                ) {
//                    stickyHeader {
//                        TopAppBarComponent()
//                    }
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
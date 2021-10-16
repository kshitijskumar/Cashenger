package com.example.cashenger.views.features.chat

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cashenger.R
import com.example.cashenger.domain.chat.models.MessageBody
import com.example.cashenger.domain.chat.models.ReplyMessageModel
import com.example.cashenger.domain.chat.models.SelfMessageModel
import com.example.cashenger.ui.theme.Blue
import com.example.cashenger.ui.theme.Grey
import com.example.cashenger.ui.theme.LightBlue
import com.example.cashenger.ui.theme.LightGrey
import com.example.cashenger.utils.FeaturesResources
import com.example.cashenger.views.components.MessageFieldComponent
import com.example.cashenger.views.components.ReplyMessageComponent
import com.example.cashenger.views.components.SelfMessageComponent
import com.example.cashenger.views.components.TopAppBarComponent
import com.example.cashenger.views.features.viewmodels.ChatViewModel

@ExperimentalFoundationApi
@Composable
fun ChatScreen(
    chatVM: ChatViewModel = viewModel()
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
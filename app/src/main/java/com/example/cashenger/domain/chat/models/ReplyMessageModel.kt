package com.example.cashenger.domain.chat.models

data class ReplyMessageModel(
    val msgText: String = "Successfully added!",
    val timeStamp: String = "22:12"
) : MessageBody {
    override val isSelf: Boolean
        get() = false
}

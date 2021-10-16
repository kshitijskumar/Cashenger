package com.example.cashenger.domain.chat.models

import com.example.cashenger.utils.FeaturesResources.getCurrentTimestamp

data class ReplyMessageModel(
    val msgText: String = "Successfully added!",
    val timeStamp: String = getCurrentTimestamp()
) : MessageBody {
    override val isSelf: Boolean
        get() = false
}

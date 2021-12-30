package com.example.tumbler.model.entity.dashboard

import com.google.gson.annotations.SerializedName

data class ReplyBody(
    @SerializedName("reply_text") var replyText: String? = null
)

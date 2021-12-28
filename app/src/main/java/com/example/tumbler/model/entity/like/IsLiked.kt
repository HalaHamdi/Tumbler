package com.example.tumbler.model.entity.like

import com.example.tumbler.model.entity.Meta
import com.google.gson.annotations.SerializedName

data class IsLiked(
    @SerializedName("meta") val meta: Meta,
    @SerializedName("response") var response: IsLikedResponse
)

data class IsLikedResponse(
    @SerializedName("like_status") var status: Boolean,
)

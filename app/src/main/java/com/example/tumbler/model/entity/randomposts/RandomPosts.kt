package com.example.tumbler.model.entity.randomposts

import com.example.tumbler.model.entity.Meta
import com.google.gson.annotations.SerializedName

data class RandomPosts(

    @SerializedName("response") val response: RandomPostsResponse,
    @SerializedName("meta") val meta: Meta
)

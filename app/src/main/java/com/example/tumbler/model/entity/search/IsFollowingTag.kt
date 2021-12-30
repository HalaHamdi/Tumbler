package com.example.tumbler.model.entity.search

import com.google.gson.annotations.SerializedName

data class IsFollowingTag(
    @SerializedName("meta") var meta: Meta,
    @SerializedName("response") var response: IsFollowing
)

data class IsFollowing(

    @SerializedName("is_following") var isFollowing: Boolean

)

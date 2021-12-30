package com.example.tumbler.model.entity.search

import com.example.tumbler.model.entity.Meta
import com.google.gson.annotations.SerializedName

data class TagsFollowed(

    @SerializedName("meta") var meta: Meta,
    @SerializedName("response") var response: TagsFollowedList

)

data class Meta(
    @SerializedName("meta") val meta: Meta,
    @SerializedName("response") val response: SuggestedTagsResponse
)

data class UserTags(

    @SerializedName("tag_description") var tagDescription: String,
    @SerializedName("tag_image") var tagImage: String

)

data class TagsFollowedList(
    @SerializedName("tags") var userTags: ArrayList<UserTags> = arrayListOf()
)

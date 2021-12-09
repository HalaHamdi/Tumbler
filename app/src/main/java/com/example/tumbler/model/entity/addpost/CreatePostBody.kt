package com.example.tumbler.model.entity.addpost

import com.google.gson.annotations.SerializedName

data class CreatePostBody(

    @SerializedName("post_status") var postStatus: String? = null,
    @SerializedName("post_time") var postTime: String? = null,
    @SerializedName("post_type") var postType: String? = null,
    @SerializedName("post_body") var postBody: String? = null
)

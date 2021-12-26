package com.example.tumbler.model.entity.createNewTumblr

import com.google.gson.annotations.SerializedName

data class CreateBlogRequest(
    @SerializedName("blog_username") val blog_username: String,
    @SerializedName("title")val title: String
    )
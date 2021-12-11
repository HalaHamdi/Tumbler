package com.example.tumbler.model.entity.randomposts

import com.google.gson.annotations.SerializedName

data class Posts(

    @SerializedName("post_id") val post_id: Int,
    @SerializedName("post_status") val post_status: String,
    @SerializedName("pinned") val pinned: Boolean,
    @SerializedName("post_time") val post_time: String,
    @SerializedName("post_type") val post_type: String,
    @SerializedName("post_body") val post_body: String,
    @SerializedName("blog_id") val blog_id: Int,
    @SerializedName("blog_username") val blog_username: String,
    @SerializedName("blog_avatar") val blog_avatar: String,
    @SerializedName("blog_avatar_shape") val blog_avatar_shape: String,
    @SerializedName("blog_title") val blog_title: String,
    @SerializedName("blog_username_asking") val blog_username_asking: String,
    @SerializedName("blog_avatar_asking") val blog_avatar_asking: String,
    @SerializedName("blog_avatar_shape_asking") val blog_avatar_shape_asking: String,
    @SerializedName("blog_title_asking") val blog_title_asking: String
)

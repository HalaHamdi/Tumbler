package com.example.tumbler.model.entity.addpost

import com.google.gson.annotations.SerializedName

data class CreatePostResponse(

    @SerializedName("post_id") var postId: Int? = null,
    @SerializedName("blog_id") var blogId: Int? = null,
    @SerializedName("blog_username") var blogUsername: String? = null,
    @SerializedName("blog_avatar") var blogAvatar: String? = null,
    @SerializedName("blog_avatar_shape") var blogAvatarShape: String? = null,
    @SerializedName("blog_title") var blogTitle: String? = null,
    @SerializedName("pinned") var pinned: Boolean? = null,
    @SerializedName("post_time") var postTime: String? = null,
    @SerializedName("post_type") var postType: String? = null,
    @SerializedName("post_body") var postBody: String? = null
)

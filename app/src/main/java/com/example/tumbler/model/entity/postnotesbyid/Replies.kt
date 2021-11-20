package com.example.tumbler.model.entity.postnotesbyid

import com.google.gson.annotations.SerializedName

data class Replies(

    @SerializedName("blog_avatar") var blogAvatar: String,
    @SerializedName("blog_avatar_shape") var blogAvatarShape: String,
    @SerializedName("blog_username") var blogUsername: String,
    @SerializedName("blog_id") var blogId: Int,
    @SerializedName("reply_id") var replyId: Int,
    @SerializedName("reply_time") var replyTime: String,
    @SerializedName("reply_text") var replyText: String

)

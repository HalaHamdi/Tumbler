package com.example.tumbler.model.entity.postnotesbyid

import com.google.gson.annotations.SerializedName

/***
 * data class to store data of the response of the back-end to get a specific post notes
 */
data class Reblogs (

   @SerializedName("post_id") var postId : Int,
   @SerializedName("blog_avatar") var blogAvatar : String,
   @SerializedName("blog_avatar_shape") var blogAvatarShape : String,
   @SerializedName("blog_username") var blogUsername : String,
   @SerializedName("blog_id") var blogId : Int,
   @SerializedName("reblog_content") var reblogContent : String

)
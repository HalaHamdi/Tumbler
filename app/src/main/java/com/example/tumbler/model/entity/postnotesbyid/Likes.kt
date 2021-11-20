package com.example.tumbler.model.entity.postnotesbyid

import com.google.gson.annotations.SerializedName

   
data class Likes (

   @SerializedName("blog_avatar") var blogAvatar : String,
   @SerializedName("blog_avatar_shape") var blogAvatarShape : String,
   @SerializedName("blog_username") var blogUsername : String,
   @SerializedName("blog_title") var blogTitle : String,
   @SerializedName("blog_id") var blogId : Int,
   @SerializedName("followed") var followed : Boolean

)
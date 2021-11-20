package com.example.tumbler.model.entity.postbyid

import com.google.gson.annotations.SerializedName

/***
 * data class to store data of the response of the back-end to get a specific post
 */
data class PostByIDResponse (

   @SerializedName("post_status") var postStatus : String,
   @SerializedName("post_id") var postId : Int,
   @SerializedName("blog_id") var blogId : Int,
   @SerializedName("blog_username") var blogUsername : String,
   @SerializedName("blog_avatar") var blogAvatar : String,
   @SerializedName("blog_avatar_shape") var blogAvatarShape : String,
   @SerializedName("blog_title") var blogTitle : String,
   @SerializedName("pinned") var pinned : Boolean,
   @SerializedName("post_time") var postTime : String,
   @SerializedName("question_body") var questionBody : String,
   @SerializedName("question_id") var questionId : Int,
   @SerializedName("question_flag") var questionFlag : Boolean,
   @SerializedName("blog_id_asking") var blogIdAsking : String,
   @SerializedName("blog_username_asking") var blogUsernameAsking : String,
   @SerializedName("blog_avatar_asking") var blogAvatarAsking : String,
   @SerializedName("blog_avatar_shape_asking") var blogAvatarShapeAsking : String,
   @SerializedName("blog_title_asking") var blogTitleAsking : String,
   @SerializedName("post_type") var postType : String,
   @SerializedName("post_body") var postBody : String,
   @SerializedName("traced_back_posts") var tracedBackPosts : List<TracedBackPosts>

)
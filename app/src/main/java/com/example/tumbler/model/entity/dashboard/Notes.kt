package com.example.tumbler.model.entity.dashboard

import com.example.tumbler.model.entity.Meta
import com.example.tumbler.model.entity.Pagination
import com.google.gson.annotations.SerializedName

data class Notes (

    @SerializedName("meta"     ) var meta     : Meta,
    @SerializedName("response" ) var response : NotesResponse

)

data class NotesResponse (

    @SerializedName("likes"   ) var likes   : Likes,
    @SerializedName("replies" ) var replies : Replies,
    @SerializedName("reblogs" ) var reblogs : Reblogs

)

data class Likes (

    @SerializedName("pagination" ) var pagination : Pagination,
    @SerializedName("likes"      ) var likes      : ArrayList<LikesPage>

)

data class LikesPage (

    @SerializedName("blog_avatar"       ) var blogAvatar      : String  ,
    @SerializedName("blog_avatar_shape" ) var blogAvatarShape : String  ,
    @SerializedName("blog_username"     ) var blogUsername    : String  ,
    @SerializedName("blog_title"        ) var blogTitle       : String  ,
    @SerializedName("blog_id"           ) var blogId          : Int     ,
    @SerializedName("followed"          ) var followed        : Boolean 

)
data class Reblogs (

    @SerializedName("pagination" ) var pagination : Pagination,
    @SerializedName("reblogs"    ) var reblogs    : ArrayList<ReblogsPage>

)

data class ReblogsPage (

    @SerializedName("post_id"           ) var postId          : Int    ,
    @SerializedName("blog_avatar"       ) var blogAvatar      : String ,
    @SerializedName("blog_avatar_shape" ) var blogAvatarShape : String ,
    @SerializedName("blog_username"     ) var blogUsername    : String ,
    @SerializedName("blog_id"           ) var blogId          : Int    ,
    @SerializedName("reblog_content"    ) var reblogContent   : String 

)


data class Replies (

    @SerializedName("pagination" ) var pagination : Pagination,
    @SerializedName("replies"    ) var replies    : ArrayList<RepliesPage>

)

data class RepliesPage (

    @SerializedName("blog_avatar"       ) var blogAvatar      : String  ,
    @SerializedName("blog_avatar_shape" ) var blogAvatarShape : String  ,
    @SerializedName("blog_username"     ) var blogUsername    : String  ,
    @SerializedName("blog_id"           ) var blogId          : Int     ,
    @SerializedName("followed"          ) var followed        : Boolean ,
    @SerializedName("reply_id"          ) var replyId         : Int     ,
    @SerializedName("reply_time"        ) var replyTime       : String  ,
    @SerializedName("reply_text"        ) var replyText       : String  

)


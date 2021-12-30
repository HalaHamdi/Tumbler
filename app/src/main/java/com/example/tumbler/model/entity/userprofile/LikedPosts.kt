package com.example.tumbler.model.entity.userprofile

import com.example.tumbler.model.entity.Meta
import com.google.gson.annotations.SerializedName

data class LikedPosts(
    @SerializedName("meta"     ) var meta     : Meta,
    @SerializedName("response" ) var response : LikedPostsResponse

)

data class LikedPostsPagination (

    @SerializedName("total"          ) var total        : Int,
    @SerializedName("count"          ) var count        : Int,
    @SerializedName("per_page"       ) var perPage      : Int,
    @SerializedName("current_page"   ) var currentPage  : Int,
    @SerializedName("total_pages"    ) var totalPages   : Int,
    @SerializedName("first_page_url" ) var firstPageUrl : Boolean,
    @SerializedName("last_page_url"  ) var lastPageUrl  : Int,
    @SerializedName("next_page_url"  ) var nextPageUrl  : String,
    @SerializedName("prev_page_url"  ) var prevPageUrl  : String

)

data class TracedBackPosts (

    @SerializedName("post_id"   ) var postId   : Int,
    @SerializedName("blog_id"   ) var blogId   : Int,
    @SerializedName("post_time" ) var postTime : String,
    @SerializedName("post_type" ) var postType : String,
    @SerializedName("post_body" ) var postBody : String

)

data class PostsLiked (

    @SerializedName("blog_username"     ) var blogUsername    : String,
    @SerializedName("blog_avatar"       ) var blogAvatar      : String,
    @SerializedName("blog_avatar_shape" ) var blogAvatarShape : String,
    @SerializedName("post_id"           ) var postId          : Int,
    @SerializedName("blog_id"           ) var blogId          : Int,
    @SerializedName("post_status"       ) var postStatus      : String,
    @SerializedName("pinned"            ) var pinned          : Boolean,
    @SerializedName("post_time"         ) var postTime        : String,
    @SerializedName("post_type"         ) var postType        : String,
    @SerializedName("post_body"         ) var postBody        : String,
    @SerializedName("traced_back_posts" ) var tracedBackPosts : ArrayList<TracedBackPosts> = arrayListOf()

)

data class LikedPostsResponse (

    @SerializedName("pagination" ) var pagination : LikedPostsPagination,
    @SerializedName("posts"      ) var likedPosts      : List<PostsLiked>

)
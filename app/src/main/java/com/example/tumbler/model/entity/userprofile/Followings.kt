package com.example.tumbler.model.entity.userprofile

import com.example.tumbler.model.entity.Meta
import com.google.gson.annotations.SerializedName

data class Followings(
    @SerializedName("meta") val meta: Meta,
    @SerializedName("response") val response: FollowingResponse
)
data class FollowingsPagination(

    @SerializedName("total") var total: Int,
    @SerializedName("count") var count: Int,
    @SerializedName("per_page") var perPage: Int,
    @SerializedName("current_page") var currentPage: Int,
    @SerializedName("total_pages") var totalPages: Int,
    @SerializedName("first_page_url") var firstPageUrl: Boolean,
    @SerializedName("last_page_url") var lastPageUrl: Int,
    @SerializedName("next_page_url") var nextPageUrl: String,
    @SerializedName("prev_page_url") var prevPageUrl: String

)

data class Following(

    @SerializedName("blog_avatar") var blogAvatar: String,
    @SerializedName("blog_avatar_shape") var blogAvatarShape: String,
    @SerializedName("blog_username") var blogUsername: String,
    @SerializedName("blog_id") var blogId: Int

)

data class FollowingResponse(
    @SerializedName("pagination") var pagination: FollowingsPagination,
    @SerializedName("followings") var followings: ArrayList<Following>
)



package com.example.tumbler.model.entity.dashboard

import com.example.tumbler.model.entity.Meta
import com.google.gson.annotations.SerializedName

data class Dashboard(
    @SerializedName("meta") val meta: Meta,
    @SerializedName("response") val response: DashboardResponse
)

data class DashboardResponse(
    @SerializedName("pagination") val pagination: DashboardPagination,
    @SerializedName("posts") val posts: List<DashboardPost>
)

data class DashboardPagination(
    @SerializedName("total") val total: Int,
    @SerializedName("count") val count: Int,
    @SerializedName("per_page") val per_page: Int,
    @SerializedName("current_page") val current_page: Int,
    @SerializedName("total_pages") val total_pages: Int,
    @SerializedName("first_page_url") val first_page_url: Boolean,
    @SerializedName("next_page_url") val next_page_url: String,
    @SerializedName("prev_page_url") val prev_page_url: String
)

data class DashboardPost(
    @SerializedName("post_id") val post_id: Int,
    @SerializedName("post_status") val post_status: String,
    @SerializedName("post_type") val post_type: String,
    @SerializedName("post_body") val post_body: String,
    @SerializedName("blog_id") val blog_id: Int,
    @SerializedName("blog_username") val blog_username: String,
    @SerializedName("blog_avatar") val blog_avatar: String,
    @SerializedName("blog_avatar_shape") val blog_avatar_shape: String,
    @SerializedName("blog_title") val blog_title: String,
    @SerializedName("post_time") val post_time: String,
    @SerializedName("is_liked") var isLiked: Boolean = false,
    @SerializedName("notes_count") var numNotes: Int = 0
)

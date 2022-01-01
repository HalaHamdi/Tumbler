package com.example.tumbler.model.entity.userprofile

data class Post(
    val blog_avatar: String,
    val blog_avatar_asking: String,
    val blog_avatar_shape: String,
    val blog_avatar_shape_asking: String,
    val blog_id: Int,
    val blog_id_asking: String,
    val blog_title: String,
    val blog_title_asking: String,
    val blog_username: String,
    val blog_username_asking: String,
    val pinned: Boolean,
    val post_body: String,
    val post_id: Int,
    val post_status: String,
    val post_time: String,
    val post_type: String,
    val question_body: String,
    val question_flag: Boolean,
    val question_id: Int,
    val traced_back_posts: List<TracedBackPost>
)

package com.example.tumbler.model.entity.SignUpResponse

data class Response(
    val id: String,
    val blog_id: String,
    val blog_username: String,
    val email: String,
    val is_verified: String,
    val blog_avatar: String,
    val access_token: String
)

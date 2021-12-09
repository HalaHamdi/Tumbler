package com.example.tumbler.model.entity.SignUpResponse

data class Response(
    val access_token: String,
    val blog_avatar: String,
    val blog_username: String,
    val email: String,
    val id: String
)
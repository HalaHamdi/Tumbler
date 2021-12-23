package com.example.tumbler.user

data class User(
    var access_token:String,
    var blog_id: Int,
    var blog_avatar: String,
    var id:Int,
    var blog_username:String,
    var is_verified: Boolean,
    var email: String
)
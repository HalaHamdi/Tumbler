package com.example.tumbler.user

/**
 * data class representing user as for the whole application to have the accessibility to user info
 * without need to deal with external files or strange syntax
 * just convert user info into ordinary data class
 */
data class User(
    var access_token: String,
    var blog_id: Int,
    var blog_avatar: String,
    var id: Int,
    var blog_username: String,
    var is_verified: Boolean,
    var email: String
)

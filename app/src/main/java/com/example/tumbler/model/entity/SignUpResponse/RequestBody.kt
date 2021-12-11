package com.example.tumbler.model.entity.SignUpResponse

import com.google.gson.annotations.SerializedName

data class RequestBody(
    @SerializedName("email")val email: String,
    @SerializedName("password")val password: String,
    @SerializedName("blog_username")val blog_username: String,
    @SerializedName("age") val age: String
)

package com.example.tumbler.model.entity.userprofile

data class Response(
    val pagination: Pagination,
    val posts: List<Post>
)
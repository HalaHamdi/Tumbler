package com.example.tumbler.model.entity.userprofile

data class Pagination(
    val count: Int,
    val current_page: Int,
    val first_page_url: Boolean,
    val next_page_url: Any,
    val per_page: Int,
    val prev_page_url: String,
    val total: Int,
    val total_pages: Int
)

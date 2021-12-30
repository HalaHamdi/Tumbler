package com.example.tumbler.model.entity.settings

data class change_password(
    val current_password: String,
    val password: String,
    val password_confirmation: String
)
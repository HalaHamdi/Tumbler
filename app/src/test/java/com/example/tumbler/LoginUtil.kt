package com.example.tumbler

object LoginUtil {
    fun validateInput(username: String,password: String): Boolean {
        if (username.isNullOrEmpty() || password.isNullOrEmpty())
            return false

        return true
    }
}
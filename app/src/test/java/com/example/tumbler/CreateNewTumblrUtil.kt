package com.example.tumbler

object CreateNewTumblrUtil {
    fun validateUsernameInput(username: String): Boolean {
        if (username.isNullOrEmpty() )
            return false

        return true
    }
}
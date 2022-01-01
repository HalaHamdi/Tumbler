package com.example.tumbler

object RegisterationUtil {
    /**
     * input is not valid if email/password/name/age is empty
     * age is less than 13
     * email form is wrong
     */
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    fun validateRegisterationInput(
        email: String,
        password: String,
        name: String,
        age: String
    ): Boolean {
        if (age.isNullOrEmpty() || name.isNullOrEmpty() || email.isNullOrEmpty() || password.isNullOrEmpty())
            return false
        else if (age.toInt() < 13)
            return false
        else if (!(email.matches(emailPattern.toRegex())))
            return false

        return true
    }
}
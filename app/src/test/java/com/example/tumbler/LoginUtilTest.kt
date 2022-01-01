package com.example.tumbler

import com.google.common.truth.Truth
import org.junit.Test


class LoginUtilTest{
    @Test
    fun emptyUsername() {
        val RESULT = LoginUtil.validateInput("", "elonsol123")
        Truth.assertThat(RESULT).isFalse()
    }

    @Test
    fun emptyPassword() {
        val RESULT = LoginUtil.validateInput("qqqqqq", "")
        Truth.assertThat(RESULT).isFalse()
    }

    @Test
    fun emptyUsernameAndPassword() {
        val RESULT = LoginUtil.validateInput("", "")
        Truth.assertThat(RESULT).isFalse()
    }

    @Test
    fun Test1() {
        val RESULT = LoginUtil.validateInput("dddd", "elonsol123")
        Truth.assertThat(RESULT).isTrue()
    }

    @Test
    fun Test2() {
        val RESULT = LoginUtil.validateInput("Elonsol", "Elonsol123112")
        Truth.assertThat(RESULT).isTrue()
    }

    @Test
    fun Test3() {
        val RESULT = LoginUtil.validateInput("Zzzzzzzz", "q2q2q2q2q2qqqq")
        Truth.assertThat(RESULT).isTrue()
    }

    @Test
    fun Test4() {
        val RESULT = LoginUtil.validateInput("Noway5", "elonsol123")
        Truth.assertThat(RESULT).isTrue()
    }

}
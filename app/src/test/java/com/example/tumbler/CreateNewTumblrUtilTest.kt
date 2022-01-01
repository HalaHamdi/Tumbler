package com.example.tumbler

import com.google.common.truth.Truth
import org.junit.Test


class CreateNewTumblrUtilTest{
    @Test
    fun emptyUsername() {
        val RESULT = CreateNewTumblrUtil.validateUsernameInput("")
        Truth.assertThat(RESULT).isFalse()
    }

    @Test
    fun test1() {
        val RESULT = CreateNewTumblrUtil.validateUsernameInput("Aaaaaaa")
        Truth.assertThat(RESULT).isTrue()
    }

    @Test
    fun test2() {
        val RESULT = CreateNewTumblrUtil.validateUsernameInput("Elonsol")
        Truth.assertThat(RESULT).isTrue()
    }

    @Test
    fun test3() {
        val RESULT = CreateNewTumblrUtil.validateUsernameInput("qwoeeoeoeoe")
        Truth.assertThat(RESULT).isTrue()
    }

    @Test
    fun test4() {
        val RESULT = CreateNewTumblrUtil.validateUsernameInput("MostafaA")
        Truth.assertThat(RESULT).isTrue()
    }

    @Test
    fun test5() {
        val RESULT = CreateNewTumblrUtil.validateUsernameInput("MANANAANANA")
        Truth.assertThat(RESULT).isTrue()
    }

    @Test
    fun test6() {
        val RESULT = CreateNewTumblrUtil.validateUsernameInput("Nebooooo")
        Truth.assertThat(RESULT).isTrue()
    }

    @Test
    fun test7() {
        val RESULT = CreateNewTumblrUtil.validateUsernameInput("Aaaan222nn")
        Truth.assertThat(RESULT).isTrue()
    }

}
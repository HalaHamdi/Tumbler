package com.example.tumbler

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RegisterationUtilTest {
    @Test
    fun emptyUsername() {
        val RESULT = RegisterationUtil.validateRegisterationInput("", "elonsol123", "sobhy", 15)
        assertThat(RESULT).isFalse()
    }
    @Test
    fun emptyPassword() {
        val RESULT = RegisterationUtil.validateRegisterationInput("elonsolmostafa@gmail.com", "", "sobhy", 15)
        assertThat(RESULT).isFalse()
    }
    @Test
    fun emptyage() {
        val RESULT = RegisterationUtil.validateRegisterationInput("nebo@gmail.com", "abbas1111111123", "Neboo", "".toInt())
        assertThat(RESULT).isFalse()
    }
    @Test
    fun emptyName() {
        val RESULT = RegisterationUtil.validateRegisterationInput("hala@gmail.com", "hala123456", "", 15)
        assertThat(RESULT).isFalse()
    }

    @Test
    fun wrongEmailFormat() {
        val RESULT = RegisterationUtil.validateRegisterationInput("Elonsol123456789", "44444123456", "mostafa", 20)
        assertThat(RESULT).isFalse()
    }

    @Test
    fun rightTest() {
        val RESULT = RegisterationUtil.validateRegisterationInput("hala@gmail.com", "hala123456", "hala", 15)
        assertThat(RESULT).isTrue()
    }

    @Test
    fun rightTest2() {
        val RESULT = RegisterationUtil.validateRegisterationInput("sobhy@gmail.com", "11123456", "elonsol", 20)
        assertThat(RESULT).isTrue()
    }

    @Test
    fun rightTest3() {
        val RESULT = RegisterationUtil.validateRegisterationInput("sobhy11111@yahoo.com", "11123zxcds456", "mostafa123", 20)
        assertThat(RESULT).isTrue()
    }
}

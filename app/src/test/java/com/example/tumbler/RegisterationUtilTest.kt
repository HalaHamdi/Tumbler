package com.example.tumbler

import com.example.tumbler.signupandin.LoginSignupFragment
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RegisterationUtilTest {
    @Test
    fun emptyUsername() {
        val RESULT = RegisterationUtil.validateRegisterationInput("", "elonsol123", "sobhy", "15")
        assertThat(RESULT).isFalse()
    }
    @Test
    fun emptyPassword() {
        val RESULT = RegisterationUtil.validateRegisterationInput("elonsolmostafa@gmail.com", "", "sobhy", "15")

        assertThat(RESULT).isFalse()
    }
    @Test
    fun emptyage() {
        val RESULT = RegisterationUtil.validateRegisterationInput("nebo@gmail.com", "abbas1111111123", "Neboo", "" )
        assertThat(RESULT).isFalse()
    }
    @Test
    fun emptyName() {
        val RESULT = RegisterationUtil.validateRegisterationInput("hala@gmail.com", "hala123456", "", "15")
        assertThat(RESULT).isFalse()
    }

    @Test
    fun wrongEmailFormat() {
        val RESULT = RegisterationUtil.validateRegisterationInput("Elonsol123456789", "44444123456", "mostafa", "20")
        assertThat(RESULT).isFalse()
    }

    @Test
    fun rightTest() {
        val RESULT = RegisterationUtil.validateRegisterationInput("hala@gmail.com", "hala123456", "hala", "15")
        assertThat(RESULT).isTrue()
    }

    @Test
    fun rightTest2() {
        val RESULT = RegisterationUtil.validateRegisterationInput("sobhy@gmail.com", "11123456", "elonsol", "20")
        assertThat(RESULT).isTrue()
    }

    @Test
    fun rightTest3() {
        val RESULT = RegisterationUtil.validateRegisterationInput("sobhy11111@yahoo.com", "11123zxcds456", "mostafa123", "20")
        assertThat(RESULT).isTrue()
    }

    @Test
    fun rightTest4() {
        val RESULT = RegisterationUtil.validateRegisterationInput("Elonsol11111@yahoo.com", "1212121212A", "Elonosl", "20")
        assertThat(RESULT).isTrue()
    }

    @Test
    fun rightTest5() {
        val RESULT = RegisterationUtil.validateRegisterationInput("mm@mm.com", "01201201211", "MMMMM", "87")
        assertThat(RESULT).isTrue()
    }

    @Test
    fun rightTest6() {
        val RESULT = RegisterationUtil.validateRegisterationInput("raaam@kraaam.com", "Mlllllllllllll", "ElonoslOFF", "20")
        assertThat(RESULT).isTrue()
    }

    @Test
    fun rightTest7() {
        val RESULT = RegisterationUtil.validateRegisterationInput("Elllll.com", "1212121212A", "Elonosl", "20")
        assertThat(RESULT).isFalse()
    }

    @Test
    fun rightTest8() {
        val RESULT = RegisterationUtil.validateRegisterationInput(".com", "1212121212A", "Elonosl", "20")
        assertThat(RESULT).isFalse()
    }

    @Test
    fun rightTest9() {
        val RESULT = RegisterationUtil.validateRegisterationInput("aaa@.com", "1212121212A", "Elonosl", "20")
        assertThat(RESULT).isFalse()
    }

    @Test
    fun rightTest10() {
        val RESULT = RegisterationUtil.validateRegisterationInput("@5a.com", "1212121212A", "Elonosl", "20")
        assertThat(RESULT).isFalse()
    }

    @Test
    fun rightTest11() {
        val RESULT = RegisterationUtil.validateRegisterationInput("Mostafa", "1212121212A", "Elonosl", "20")
        assertThat(RESULT).isFalse()
    }

    @Test
    fun rightTest12() {
        val RESULT = RegisterationUtil.validateRegisterationInput("Mostafa@", "1212121212A", "Elonosl", "20")
        assertThat(RESULT).isFalse()
    }

    @Test
    fun rightTest13() {
        val RESULT = RegisterationUtil.validateRegisterationInput("@", "1212121212A", "Elonosl", "20")
        assertThat(RESULT).isFalse()
    }

    @Test
    fun rightTest14() {
        val RESULT = RegisterationUtil.validateRegisterationInput("Mostaf@.", "1212121212A", "Elonosl", "20")
        assertThat(RESULT).isFalse()
    }

    @Test
    fun rightTest15() {
        val RESULT = RegisterationUtil.validateRegisterationInput("Zh2GDN.ccc", "1212121212A", "Elonosl", "20")
        assertThat(RESULT).isFalse()
    }

    @Test
    fun rightTest16() {
        val RESULT = RegisterationUtil.validateRegisterationInput("ZZ", "1212121212A", "Elonosl", "20")
        assertThat(RESULT).isFalse()
    }

    @Test
    fun rightTest17() {
        val RESULT = RegisterationUtil.validateRegisterationInput("Lalalalalaala@lalalalala.", "1212121212A", "Elonosl", "20")
        assertThat(RESULT).isFalse()
    }

    @Test
    fun rightTest18() {
        val RESULT = RegisterationUtil.validateRegisterationInput("Done@.Done", "1212121212A", "Elonosl", "20")
        assertThat(RESULT).isFalse()
    }

    @Test
    fun rightTest19() {
        val RESULT = RegisterationUtil.validateRegisterationInput(".com.com@", "1212121212A", "Elonosl", "20")
        assertThat(RESULT).isFalse()
    }

    @Test
    fun rightTest20() {
        val RESULT = RegisterationUtil.validateRegisterationInput("Elll@ell.com", "1212121212A", "Elonosl", "5")
        assertThat(RESULT).isFalse()
    }

    @Test
    fun rightTest21() {
        val RESULT = RegisterationUtil.validateRegisterationInput("7Elll@ell.com", "1212121212A", "Elonosl", "5")
        assertThat(RESULT).isFalse()
    }

    @Test
    fun rightTest22() {
        val RESULT = RegisterationUtil.validateRegisterationInput("Elll@88ell.com", "1212121212A", "Elonosl", "5")
        assertThat(RESULT).isFalse()
    }

    @Test
    fun rightTest23() {
        val RESULT = RegisterationUtil.validateRegisterationInput("Sa@elsqqq.com", "1212121212A", "Elonosl", "5")
        assertThat(RESULT).isFalse()
    }

    @Test
    fun rightTest24() {
        val RESULT = RegisterationUtil.validateRegisterationInput("addd@dd.com", "1212121212A", "Elonosl", "55")
        assertThat(RESULT).isTrue()
    }
}

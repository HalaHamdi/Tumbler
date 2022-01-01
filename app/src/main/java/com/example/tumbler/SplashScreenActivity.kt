package com.example.tumbler

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.tumbler.signupandin.IntroSliderActivity

class SplashScreenActivity : AppCompatActivity() {
    lateinit var handler: Handler
    /**
     * Splash screen of the application
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        handler = Handler()
        handler.postDelayed(
            {

                val INTENT = Intent(this, IntroSliderActivity::class.java)
                startActivity(INTENT)
                finish()
            },
            2000
        ) // delaying 1 millisecond to to open main activity
    }
}

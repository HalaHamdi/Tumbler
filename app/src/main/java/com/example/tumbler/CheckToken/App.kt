package com.example.tumbler.CheckToken

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import com.example.tumbler.MainActivity
import com.example.tumbler.SplashScreenActivity
import com.example.tumbler.UserPagesActivity

class App: AppCompatActivity() {
    var myshered : SharedPreferences? =null
    override fun onStart() {
        super.onStart()
        myshered = getSharedPreferences("myshared", 0)
        var token = myshered?.getString("access_token","")

        if(token != ""){
            val INTENT = Intent(this, UserPagesActivity::class.java)
            startActivity(INTENT)
        }else if (token == ""){
            val INTENT = Intent(this, SplashScreenActivity::class.java)
            startActivity(INTENT)
        }
    }
}
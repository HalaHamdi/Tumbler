package com.example.tumbler.CheckToken

import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.tumbler.BaseApplication
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
            (application as BaseApplication).setUser(
                myshered?.getString("access_token","").toString(),
                myshered?.getString("blog_id","").toString().toInt(),
                myshered?.getString("blog_avatar","").toString(),
                myshered?.getString("id","").toString().toInt(),
                myshered?.getString("blog_username","").toString(),
                myshered?.getString("is_verified","").toString().toBoolean(),
                myshered?.getString("email","").toString()
            )
            val INTENT = Intent(this, UserPagesActivity::class.java)
            startActivity(INTENT)
        }else if (token == ""){
            val INTENT = Intent(this, SplashScreenActivity::class.java)
            startActivity(INTENT)
        }
    }
}
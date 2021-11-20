package com.example.tumbler
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        To navigate between the fragments
        val navController = findNavController(R.id.contentFragment)
        val btnNavView = findViewById<BottomNavigationView>(R.id.footerNavigation)
        btnNavView.setupWithNavController(navController)

//        To navigate to the CreatePostActivity when clicking the button
        findViewById<FloatingActionButton>(R.id.createPostButton).setOnClickListener {
            val intent = Intent(this, CreatePostActivity::class.java)
            startActivity(intent)
        }
    }
}

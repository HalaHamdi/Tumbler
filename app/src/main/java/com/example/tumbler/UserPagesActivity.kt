package com.example.tumbler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.tumbler.databinding.ActivityUserPagesBinding

class UserPagesActivity : AppCompatActivity() {

    lateinit var binding: ActivityUserPagesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserPagesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navController = findNavController(R.id.userPagesFragment)
        binding.footerNavigation.setupWithNavController(navController)

        binding.createPostButton.setOnClickListener{
            val intent= Intent(this,CreatePostActivity::class.java)
            startActivity(intent)
        }
    }
}

package com.example.tumbler.search

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tumbler.databinding.ActivitySearchingBinding

class SearchingActivity : AppCompatActivity() {

    lateinit var binding: ActivitySearchingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}

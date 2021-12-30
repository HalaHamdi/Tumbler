package com.example.tumbler.search

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tumbler.databinding.ActivityFollowedTagsEditBinding
import org.koin.android.viewmodel.ext.android.viewModel

class FollowedTagsEditActivity : AppCompatActivity() {

    private val viewModel: FollowedTagsEditViewModel by viewModel()

    lateinit var binding: ActivityFollowedTagsEditBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFollowedTagsEditBinding.inflate(layoutInflater)

        val view = binding.root
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

        setContentView(view)
        binding.lifecycleOwner = this
        binding.viewModelFollowedTags = this.viewModel

        val adapter = FollowedTagsEditAdapter(viewModel)
        binding.followdTagsEditRv.adapter = adapter

        viewModel.getUserTagsEdit()

        viewModel.userTagsEditLiveData.observe(
            this,
            androidx.lifecycle.Observer {
                it?.let {
                    adapter.setList(it)
                }
            }
        )
    }
}

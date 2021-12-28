package com.example.tumbler.search

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.tumbler.CreatePostActivity
import com.example.tumbler.databinding.FragmentSearchBinding
import org.koin.android.viewmodel.ext.android.sharedViewModel

class SearchFragment : Fragment() {

    private val viewModel: SearchViewModel by sharedViewModel()
    lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSearchBinding.inflate(inflater)

        //listining on the search button
        binding.seacrhButton.setOnClickListener {
            val intent = Intent(activity,SearchingActivity::class.java)
            startActivity(intent)
        }

        binding.lifecycleOwner = this

        // for data binding
        binding.viewModel = this.viewModel

        val adapter = RecommendedBlogsAdapter(viewModel)
        binding.rvRecommendedBlogs.adapter = adapter

        val tagsadapt = RecommendedTagsAdapter(viewModel)
        binding.rvRecommendedTags.adapter = tagsadapt

        viewModel.getRecommendedBlogs()
        viewModel.getRecommendedTags()

        // for UI modification
        viewModel.blogsLiveData.observe(
            viewLifecycleOwner,
            Observer {
                it?.let {
                    adapter.setList(it)
                }
            }
        )

        viewModel.tagsLiveData.observe(
            viewLifecycleOwner,
            Observer {
                it?.let {
                    tagsadapt.setList(it)
                }
            }
        )

        return binding.root
    }
}

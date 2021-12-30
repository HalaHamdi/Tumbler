package com.example.tumbler.userprofile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tumbler.databinding.FragmentLikesBinding
import com.example.tumbler.databinding.FragmentPostsBinding
import org.koin.android.viewmodel.ext.android.sharedViewModel
import java.util.Observer

class LikesFragment : Fragment() {
    lateinit var binding: FragmentLikesBinding
    private val viewModel: LikesViewModel by sharedViewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLikesBinding.inflate(inflater, container, false)

        val adapter=LikesAdapter(viewModel)
        binding.rvLikedposts.adapter=adapter
        viewModel.getlikedPosts()

        viewModel.likesPostsLiveData.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer {
                it?.let {
                    adapter.setList(it)
                }
            }
        )
        return binding.root
    }
}



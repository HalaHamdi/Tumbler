package com.example.tumbler.userprofile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.example.tumbler.databinding.FragmentFollowingBinding
import org.koin.android.viewmodel.ext.android.sharedViewModel
import java.util.Observer

class FollowingFragment : Fragment() {

    private val viewModel:FollowingViewModel by  sharedViewModel()
    lateinit var binding: FragmentFollowingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFollowingBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel= this.viewModel
        val adapter=FollowingAdapter(viewModel)
        binding.rvFollowings.adapter=adapter

        viewModel.getFollowings()

        viewModel.followingLiveData.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            it?.let {
                adapter.setList(it)
            }
        })


        return binding.root
    }
}


package com.example.tumbler.userprofile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.tumbler.R
import com.example.tumbler.databinding.FragmentUserBlogBinding

class UserBlogFragment : Fragment() {
   lateinit var binding : FragmentUserBlogBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentUserBlogBinding.inflate(inflater,container,false)
        binding.chatIcon.setOnClickListener{view:View->
            view.findNavController().navigate(UserBlogFragmentDirections.actionUserBlogFragmentToChatFragment())
        }
        return binding.root
    }
}
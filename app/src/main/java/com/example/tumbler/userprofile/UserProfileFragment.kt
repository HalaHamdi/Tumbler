package com.example.tumbler.userprofile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tumbler.databinding.FragmentUserProfileBinding

class UserProfileFragment : Fragment() {
    lateinit var binding: FragmentUserProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserProfileBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }
}

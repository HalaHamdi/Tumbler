package com.example.tumbler

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tumbler.R
import com.example.tumbler.databinding.FragmentCreateNewTumblrBinding


class CreateNewTumblrFragment : Fragment() {
    lateinit var binding:FragmentCreateNewTumblrBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentCreateNewTumblrBinding.inflate(inflater,container,false)
        return binding.root
    }
}
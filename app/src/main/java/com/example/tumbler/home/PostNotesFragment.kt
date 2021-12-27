package com.example.tumbler.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tumbler.R
import com.example.tumbler.databinding.FragmentPostNotesBinding


class PostNotesFragment : Fragment() {
   lateinit var binding: FragmentPostNotesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPostNotesBinding.inflate(inflater,container,false)
        return binding.root
    }


}
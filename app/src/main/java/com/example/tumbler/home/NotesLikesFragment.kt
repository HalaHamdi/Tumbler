package com.example.tumbler.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tumbler.databinding.FragmentNotesLikesBinding

class NotesLikesFragment : Fragment() {

    lateinit var binding: FragmentNotesLikesBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNotesLikesBinding.inflate(inflater, container, false)
        return binding.root
    }
}

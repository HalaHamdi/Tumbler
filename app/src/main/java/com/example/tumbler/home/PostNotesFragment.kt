package com.example.tumbler.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.tumbler.databinding.FragmentPostNotesBinding

class PostNotesFragment : Fragment() {
    lateinit var binding: FragmentPostNotesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPostNotesBinding.inflate(inflater, container, false)
        binding.likesTxt.setOnClickListener { view: View ->
            view.findNavController().navigate(PostNotesFragmentDirections.actionPostNotesFragmentToNotesLikesFragment())
        }
        binding.reblogsTxt.setOnClickListener { view: View ->
            view.findNavController().navigate(PostNotesFragmentDirections.actionPostNotesFragmentToNotesReblogsFragment())
        }
        return binding.root
    }
}

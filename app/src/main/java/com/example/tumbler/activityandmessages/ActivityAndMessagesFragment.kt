package com.example.tumbler.activityandmessages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.tumbler.databinding.FragmentActivityAndMessagesBinding

class ActivityAndMessagesFragment : Fragment() {
    lateinit var binding: FragmentActivityAndMessagesBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentActivityAndMessagesBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        binding.testButton.setOnClickListener{ view:View ->
            view.findNavController().navigate(ActivityAndMessagesFragmentDirections.actionActivityAndMessagesFragmentToCreateNewTumblrFragment())

        }
        return binding.root
    }
}

package com.example.tumbler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
        return binding.root
    }
}

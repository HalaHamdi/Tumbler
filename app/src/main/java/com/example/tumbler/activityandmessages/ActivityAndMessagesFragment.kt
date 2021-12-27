package com.example.tumbler.activityandmessages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import co.intentservice.chatui.ChatView
import co.intentservice.chatui.ChatView.OnSentMessageListener
import com.example.tumbler.UserPagesActivity
import com.example.tumbler.databinding.FragmentActivityAndMessagesBinding
import co.intentservice.chatui.models.ChatMessage
import com.example.tumbler.R
import com.google.android.material.textfield.TextInputEditText
import org.w3c.dom.Text


class ActivityAndMessagesFragment : Fragment() {
    lateinit var binding: FragmentActivityAndMessagesBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentActivityAndMessagesBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        binding.chatBtn.setOnClickListener{ view:View->
            view.findNavController().navigate(ActivityAndMessagesFragmentDirections.actionActivityAndMessagesFragmentToChat2Fragment())
        }
        binding.chatBtn2.setOnClickListener{ view:View->
            view.findNavController().navigate(ActivityAndMessagesFragmentDirections.actionActivityAndMessagesFragmentToChatFragment())
        }



        return binding.root
    }
}

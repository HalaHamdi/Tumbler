package com.example.tumbler.activityandmessages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tumbler.UserPagesActivity
import com.example.tumbler.databinding.FragmentChatBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class ChatFragment : Fragment() {
    lateinit var binding: FragmentChatBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentChatBinding.inflate(inflater, container, false)
        val navBar: BottomNavigationView = (requireActivity() as UserPagesActivity).binding.footerNavigation
        val fab = (requireActivity() as UserPagesActivity).binding.createPostButton
        fab.visibility = GONE
        navBar.visibility = GONE
        binding.chatView.setOnSentMessageListener { // perform actual message sending
            true
        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        val navBar: BottomNavigationView = (requireActivity() as UserPagesActivity).binding.footerNavigation
        val fab = (requireActivity() as UserPagesActivity).binding.createPostButton
        fab.visibility = View.VISIBLE
        navBar.visibility = View.VISIBLE
    }
}

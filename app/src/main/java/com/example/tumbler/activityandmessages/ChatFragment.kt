package com.example.tumbler.activityandmessages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tumbler.R
import com.example.tumbler.UserPagesActivity
import com.example.tumbler.databinding.FragmentChatBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.view.View.GONE
import co.intentservice.chatui.fab.FloatingActionButton
import com.example.tumbler.MainActivity
import com.google.android.material.navigation.NavigationView





class ChatFragment : Fragment() {
    lateinit var binding: FragmentChatBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentChatBinding.inflate(inflater,container,false)
        val navBar: BottomNavigationView = (requireActivity() as UserPagesActivity).binding.footerNavigation
        val fab = (requireActivity() as UserPagesActivity).binding.createPostButton
        fab.visibility = GONE
        navBar.visibility= GONE
        return binding.root
    }


}
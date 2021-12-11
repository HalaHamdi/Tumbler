package com.example.tumbler.signupandin

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tumbler.R
import com.example.tumbler.UserPagesActivity
import com.example.tumbler.databinding.FragmentSignupAgeandUsernameBinding


class SignupAgeandUsername : Fragment() {
    lateinit var binding:FragmentSignupAgeandUsernameBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentSignupAgeandUsernameBinding.inflate(inflater,container,false)

        binding.AgeUsernameDoneBtn.setOnClickListener{view:View ->
            val INTENT = Intent(this.context, UserPagesActivity::class.java)
            startActivity(INTENT)
        }
        return binding.root
    }

}
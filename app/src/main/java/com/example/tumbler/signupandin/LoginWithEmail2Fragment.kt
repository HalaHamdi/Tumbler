package com.example.tumbler.signupandin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.tumbler.databinding.FragmentLoginWithEmail2Binding

class LoginWithEmail2Fragment : Fragment() {
    lateinit var binding: FragmentLoginWithEmail2Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginWithEmail2Binding.inflate(inflater, container, false)
//        binding.login2MagicLinkBtn.setOnClickListener { view: View ->
//            view.findNavController().navigate(LoginWithEmail2FragmentDirections.actionLoginWithEmail2FragmentToMagicLinkFragment())
//        }
//
//        binding.login2PasswordBtn.setOnClickListener { view: View ->
//            view.findNavController().navigate(LoginWithEmail2FragmentDirections.actionLoginWithEmail2FragmentToLoginWithEmail4Fragment())
//        }
        return binding.root
    }
}

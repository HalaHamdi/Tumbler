package com.example.tumbler.signupandin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.tumbler.databinding.FragmentLoginWithEmailOrGoogleBinding

class LoginWithEmailOrGoogleFragment : Fragment() {
    lateinit var binding: FragmentLoginWithEmailOrGoogleBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginWithEmailOrGoogleBinding.inflate(inflater, container, false)

        var LoginWithEmailAction = LoginWithEmailOrGoogleFragmentDirections.actionLoginWithEmailOrGoogleFragmentToLoginWithEmailFragment()
        binding.loginSignupLoginWithEmailBtn.setOnClickListener { view: View ->
            view.findNavController().navigate(LoginWithEmailAction)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginSignupLoginWithEmailBtn.animate().translationY(-250f).alpha(100f).duration = 600
        binding.loginSignupLoginWithGoogleBtn.animate().translationY(-250f).alpha(100f).duration = 600
    }
}

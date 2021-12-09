package com.example.tumbler.signupandin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.tumbler.databinding.FragmentSignupWithEmailOrGoogleBinding

class SignupWithEmailOrGoogleFragment : Fragment() {
    lateinit var binding: FragmentSignupWithEmailOrGoogleBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignupWithEmailOrGoogleBinding.inflate(inflater, container, false)
        var SignupWithEmailAction =
            SignupWithEmailOrGoogleFragmentDirections.actionSignupWithEmailOrGoogleFragmentToSignUpFragment()
        binding.loginSignupSignupWithEmailBtn.setOnClickListener { view: View ->
            view.findNavController().navigate(SignupWithEmailAction)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginSignupSignupWithEmailBtn.animate().translationY(-250f).alpha(100f).duration = 600
        binding.loginSignupSignupWithGoogleBtn.animate().translationY(-250f).alpha(100f).duration = 600
    }
}

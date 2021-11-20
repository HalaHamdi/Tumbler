package com.example.tumbler.signupandin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.tumbler.databinding.FragmentLoginSignupBinding

class LoginSignupFragment : Fragment() {
    lateinit var binding: FragmentLoginSignupBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLoginSignupBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment

        // action from signup_login page to signup page
        var SignupAction = LoginSignupFragmentDirections.actionLoginSignupFragmentToSignUpFragment()
        binding.loginSignupSignUpWithEmailBtn.setOnClickListener { view: View ->
            view.findNavController().navigate(SignupAction)
        }
        // OnClick ----> Signup page
        binding.loginSignupSignupBtn.setOnClickListener {
            binding.loginSignupSignupBtn.animate().translationY(-500f).alpha(0f).duration = 500
            binding.loginSignupLoginBtn.animate().translationY(-500f).alpha(0f).duration = 500
            binding.loginSignupLoginBtn.setEnabled(false)
            binding.loginSignupSignupBtn.setEnabled(false)

            binding.loginSignupSignUpWithEmailBtn.animate().translationY(-250f).alpha(100f).duration = 1000
            binding.loginSignupSignUpWithGoogleBtn.animate().translationY(-250f).alpha(100f).duration = 1000
            binding.loginSignupSignUpWithEmailBtn.setEnabled(true)
            binding.loginSignupSignUpWithGoogleBtn.setEnabled(true)
        }

        var LoginAction =
            LoginSignupFragmentDirections.actionLoginSignupFragmentToLoginWithEmailFragment()
        binding.loginSignupLoginWithEmailBtn.setOnClickListener { view: View ->
            view.findNavController().navigate(LoginAction)
        }
        binding.loginSignupLoginBtn.setOnClickListener {
            binding.loginSignupSignupBtn.animate().translationY(-500f).alpha(0f).duration = 500
            binding.loginSignupLoginBtn.animate().translationY(-500f).alpha(0f).duration = 500
            binding.loginSignupLoginBtn.setEnabled(false)
            binding.loginSignupSignupBtn.setEnabled(false)

            binding.loginSignupLoginWithEmailBtn.animate().translationY(-250f).alpha(100f).duration = 1000
            binding.loginSignupLoginWithGoogleBtn.animate().translationY(-250f).alpha(100f).duration = 1000
            binding.loginSignupLoginWithEmailBtn.setEnabled(true)
            binding.loginSignupLoginWithGoogleBtn.setEnabled(true)
        }
        return binding.root
    }
}

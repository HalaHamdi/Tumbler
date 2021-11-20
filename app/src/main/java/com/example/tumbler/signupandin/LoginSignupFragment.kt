package com.example.tumbler.signupandin

import android.os.Bundle
import android.os.Handler
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
        ButtonsAnimation1()

        ButtonsAnimation2()

        return binding.root
    }

    /**
     * sign up button onClickListener We make an animation
     * we make a delay to see the animation
     * then we navigate to our destination
     */
    fun ButtonsAnimation1() {
        var SignupAction = LoginSignupFragmentDirections.actionLoginSignupFragmentToSignupWithEmailOrGoogleFragment()
        binding.loginSignupSignupBtn.setOnClickListener { view: View ->
            binding.loginSignupLoginBtn.animate().translationY(-600f).alpha(0f).duration = 1000
            binding.loginSignupSignupBtn.animate().translationY(-600f).alpha(0f).duration = 1000
            Handler().postDelayed({ view.findNavController().navigate(SignupAction) }, 400)
        }
    }

    /**
     * log in button onClickListener We make an animation
     * we make a delay to see the animation
     * then we navigate to our destination
     */
    fun ButtonsAnimation2() {
        var LoginAction = LoginSignupFragmentDirections.actionLoginSignupFragmentToLoginWithEmailOrGoogleFragment()
        binding.loginSignupLoginBtn.setOnClickListener { view: View ->
            binding.loginSignupLoginBtn.animate().translationY(-600f).alpha(0f).duration = 1000
            binding.loginSignupSignupBtn.animate().translationY(-600f).alpha(0f).duration = 1000
            Handler().postDelayed({ view.findNavController().navigate(LoginAction) }, 400)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginSignupSignupBtn.animate().translationY(-200f).alpha(100f).duration = 1000
        binding.loginSignupLoginBtn.animate().translationY(-200f).alpha(100f).duration = 1000
    }
}

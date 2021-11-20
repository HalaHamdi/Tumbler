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
        var SignupAction=LoginSignupFragmentDirections.actionLoginSignupFragmentToSignupWithEmailOrGoogleFragment()
        binding.loginSignupSignupBtn.setOnClickListener { view:View->
            binding.loginSignupLoginBtn.animate().translationY(-600f).alpha(0f).duration = 1000
            binding.loginSignupSignupBtn.animate().translationY(-600f).alpha(0f).duration = 1000
            Handler().postDelayed({ view.findNavController().navigate(SignupAction) }, 400)
        }
//        }

            var LoginAction = LoginSignupFragmentDirections.actionLoginSignupFragmentToLoginWithEmailOrGoogleFragment()
            binding.loginSignupLoginBtn.setOnClickListener { view:View->
                binding.loginSignupLoginBtn.animate().translationY(-600f).alpha(0f).duration = 1000
                binding.loginSignupSignupBtn.animate().translationY(-600f).alpha(0f).duration = 1000
                Handler().postDelayed({ view.findNavController().navigate(LoginAction) }, 400)
            }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginSignupSignupBtn.animate().translationY(-200f).alpha(100f).duration = 1000
        binding.loginSignupLoginBtn.animate().translationY(-200f).alpha(100f).duration = 1000
    }
}

package com.example.tumbler.signupandin
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.tumbler.databinding.FragmentLoginWithEmailBinding

class LoginWithEmailFragment : Fragment() {
    lateinit var binding: FragmentLoginWithEmailBinding
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginWithEmailBinding.inflate(inflater, container, false)
        binding.loginContinueBtn.setOnClickListener { view:View ->
            if(binding.loginTxtEmail.text.isNullOrEmpty())
            {
                binding.loginErrorTxt.visibility=View.GONE
                Toast.makeText(context, "Oops! You forgot to enter your email address!", Toast.LENGTH_SHORT).show()
            }
            else if (!(binding.loginTxtEmail.text.toString().matches(emailPattern.toRegex())))
            {
                binding.loginErrorTxt.visibility=View.VISIBLE
                binding.loginErrorTxt.text="That email doesn't have a Tumblr account. Sign up now?"
            }
            else {
                binding.loginErrorTxt.visibility=View.GONE
                view.findNavController().navigate(LoginWithEmailFragmentDirections.actionLoginWithEmailFragmentToLoginWithEmail2Fragment())
            }
        }

        binding.loginErrorTxt.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(LoginWithEmailFragmentDirections.actionLoginWithEmailFragmentToSignUpFragment())
        }
        return binding.root
    }

}

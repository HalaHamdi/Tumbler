package com.example.tumbler

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.tumbler.databinding.FragmentLoginWithEmail4Binding


class LoginWithEmail4Fragment : Fragment() {
    lateinit var binding:FragmentLoginWithEmail4Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentLoginWithEmail4Binding.inflate(inflater,container,false)
        binding.loginWithEmail4ForgetPasswordTxt.setOnClickListener { view:View->
            view.findNavController().navigate(LoginWithEmail4FragmentDirections.actionLoginWithEmail4FragmentToForgetPasswordFragment())
        }

        binding.loginWithEmail4LoginTxt.setOnClickListener {
            if(binding.login4TxtEmail.text.isNullOrEmpty() || binding.login4TxtPassword.text.isNullOrEmpty())
            {
                Toast.makeText(context, "missed inputs", Toast.LENGTH_SHORT).show()
            }
            else
            {
                val INTENT = Intent (this.context,UserPagesActivity::class.java)
                startActivity(INTENT)
            }
        }
        return binding.root
    }

}
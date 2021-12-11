package com.example.tumbler.signupandin.Login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.tumbler.UserPagesActivity
import com.example.tumbler.databinding.FragmentLoginWithEmail4Binding
import com.example.tumbler.model.entity.LoginResponse.LoginRequest
import com.example.tumbler.model.entity.SignUpResponse.RequestBody
import com.example.tumbler.signupandin.SignUp.SignupViewModel

class LoginWithEmail4Fragment : Fragment() {

    private val viewModel: LoginViewModel by lazy {
        ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    lateinit var binding: FragmentLoginWithEmail4Binding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginWithEmail4Binding.inflate(inflater, container, false)
        binding.loginWithEmail4ForgetPasswordTxt.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(LoginWithEmail4FragmentDirections.actionLoginWithEmail4FragmentToForgetPasswordFragment())
        }


        viewModel.LoginLiveData.observe(viewLifecycleOwner, Observer {
            Log.i("Elonsol11", it.meta.status.toString())
            if (it.meta.status.toInt() == 422 || it.meta.status.toInt() == 500) {
                Log.i("Elonsol", "Inside condition")
                Toast.makeText(context, it.meta.msg, Toast.LENGTH_SHORT).show()
            } else {

                Toast.makeText(context, it.meta.msg, Toast.LENGTH_SHORT).show()
                val INTENT = Intent(this.context, UserPagesActivity::class.java)
                startActivity(INTENT)
            }
        })

        binding.loginWithEmail4LoginTxt.setOnClickListener {
            if (binding.login4TxtEmail.text.isNullOrEmpty() || binding.login4TxtPassword.text.isNullOrEmpty()) {
                Toast.makeText(context, "missed inputs", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.Login(
                    LoginRequest(
                        binding.login4TxtEmail.text.toString(),
                        binding.login4TxtPassword.text.toString()
                    )
                )
            }
        }

        return binding.root
    }
}

package com.example.tumbler.signupandin

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.tumbler.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {
    lateinit var binding: FragmentSignUpBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)

        binding.signUpWithEmailLoginTxt.setOnClickListener {
            val ALERTDIALOGBUILDER: AlertDialog.Builder = AlertDialog.Builder(activity)
            ALERTDIALOGBUILDER.setMessage("Hold on! Are you sure you have another account? it would stink to lose everything you just followed.")
            ALERTDIALOGBUILDER.setCancelable(false)

            ALERTDIALOGBUILDER.setPositiveButton(
                Html.fromHtml("<font color='#E91E63'>" + "I'm sure"),
                DialogInterface.OnClickListener { dialog, which ->
                    // ...
                    findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToLoginSignupFragment())
                }
            )
            ALERTDIALOGBUILDER.setNegativeButton(
                Html.fromHtml("<font color='#504F4F'>" + "Nevermind"),
                DialogInterface.OnClickListener { dialog, which ->
                    // Do nothing
                }
            )

            val ALERTDIALOG = ALERTDIALOGBUILDER.create()
            ALERTDIALOG.show()
        }

        binding.signUpDoneTxt.setOnClickListener{ view:View ->
            if(binding.signupTxtAge.text.isNullOrEmpty() || binding.signupTxtName.text.isNullOrEmpty()
                || binding.signupTxtEmail.text.isNullOrEmpty() || binding.signupTxtPassword.text.isNullOrEmpty()) {
                Toast.makeText(context, "missed input", Toast.LENGTH_SHORT).show()
            }
            else {

                    view.findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToHomeFragment())
                }

        }
        return binding.root
    }

    fun validateInputs()
    {
        if(binding.signupTxtAge.text.isNullOrEmpty() || binding.signupTxtName.text.isNullOrEmpty()
                    || binding.signupTxtEmail.text.isNullOrEmpty() || binding.signupTxtPassword.text.isNullOrEmpty())

                        binding.signUpDoneTxt.isEnabled=false
    }
}

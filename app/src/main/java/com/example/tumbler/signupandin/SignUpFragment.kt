package com.example.tumbler.signupandin

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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

        binding.signupTxtLogin.setOnClickListener {
            val alertDialogBuilder: AlertDialog.Builder = AlertDialog.Builder(activity)
            alertDialogBuilder.setMessage("Hold on! Are you sure you have another account? it would stink to lose everything you just followed.")
            alertDialogBuilder.setCancelable(false)

            alertDialogBuilder.setPositiveButton(
                Html.fromHtml("<font color='#E91E63'>" + "I'm sure"),
                DialogInterface.OnClickListener { dialog, which ->
                    // ...
                    findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToLoginSignupFragment())
                }
            )
            alertDialogBuilder.setNegativeButton(
                Html.fromHtml("<font color='#504F4F'>" + "Nevermind"),
                DialogInterface.OnClickListener { dialog, which ->
                    // Do nothing
                }
            )

            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        }
        return binding.root
    }
}

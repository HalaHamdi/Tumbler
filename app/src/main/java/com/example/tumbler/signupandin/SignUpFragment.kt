package com.example.tumbler.signupandin

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tumbler.UserPagesActivity
import com.example.tumbler.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {
    lateinit var binding: FragmentSignUpBinding
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)

        // Alert Dialog appears When the user clicks on log in text in Sign up Page
        binding.signUpWithEmailLoginTxt.setOnClickListener {
            val ALERTDIALOGBUILDER: AlertDialog.Builder = AlertDialog.Builder(activity)
            ALERTDIALOGBUILDER.setMessage("Hold on! Are you sure you have another account? it would stink to lose everything you just followed.")
            ALERTDIALOGBUILDER.setCancelable(false)

            ALERTDIALOGBUILDER.setPositiveButton(
                Html.fromHtml("<font color='#E91E63'>" + "I'm sure"), DialogInterface.OnClickListener { dialog, which -> findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToLoginSignupFragment()) }
            )
            ALERTDIALOGBUILDER.setNegativeButton(Html.fromHtml("<font color='#504F4F'>" + "Nevermind"), DialogInterface.OnClickListener { dialog, which -> })
            val ALERTDIALOG = ALERTDIALOGBUILDER.create()
            ALERTDIALOG.show()
        }

        // Navigate from Sign up page to Home Page
        ValidateInputs()
        return binding.root
    }

    /**
     * validate email/password/name/age
     * all of the inputs can't be null
     * age can't be less than 13
     * email must be in the right format
     * different messages appear based on the condition
     * then if everything is valid we navigate to home page
     */
    fun ValidateInputs() {
        binding.signUpDoneTxt.setOnClickListener { view: View ->
            if (binding.signupTxtAge.text.isNullOrEmpty() || binding.signupTxtName.text.isNullOrEmpty() ||
                binding.signupTxtEmail.text.isNullOrEmpty() || binding.signupTxtPassword.text.isNullOrEmpty()
            ) {
                Toast.makeText(context, "missed input", Toast.LENGTH_SHORT).show()
            } else if (binding.signupTxtAge.text.toString().toInt() <13) {
                Toast.makeText(context, "age is less than 13, Try again", Toast.LENGTH_SHORT).show()
            } else if (!(binding.signupTxtEmail.text.toString().matches(emailPattern.toRegex()))) {
                Toast.makeText(context, "Invalid email", Toast.LENGTH_SHORT).show()
            } else {
                val INTENT = Intent(this.context, UserPagesActivity::class.java)
                startActivity(INTENT)
            }
        }
    }
}

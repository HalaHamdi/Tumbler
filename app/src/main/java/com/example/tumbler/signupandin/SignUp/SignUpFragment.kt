package com.example.tumbler.signupandin.SignUp

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.tumbler.UserPagesActivity
import com.example.tumbler.databinding.FragmentSignUpBinding
import com.example.tumbler.home.HomeViewModel
import com.example.tumbler.model.entity.SignUpResponse.RequestBody

class SignUpFragment : Fragment() {

    private val viewModel: SignupViewModel by lazy {
        ViewModelProvider(this).get(SignupViewModel::class.java)
    }

    lateinit var binding: FragmentSignUpBinding
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        // Alert Dialog appears When the user clicks on log in text in Sign up Page
        AlertDialog()

//        viewModel.SignUpSuccesfulLiveData.observe(viewLifecycleOwner,Observer{
//            if(it.meta.status == "422" || it.meta.status == "500"){
//                Log.i("Elonsol","Inside condition")
//                Toast.makeText(context, it.meta.msg, Toast.LENGTH_SHORT).show()
//            }

//        })

        viewModel.SignUpLiveData.observe(viewLifecycleOwner,Observer{
            Log.i("Elonsol11",it.meta.status.toString())
            if(it.meta.status.toInt() == 422 || it.meta.status.toInt() == 500){
                Log.i("Elonsol","Inside condition")
                Toast.makeText(context, it.meta.msg, Toast.LENGTH_SHORT).show()
            }
            else {

                Toast.makeText(context, it.meta.msg, Toast.LENGTH_SHORT).show()
                val INTENT = Intent(this.context, UserPagesActivity::class.java)
                startActivity(INTENT)
            }

        })

        binding.signUpDoneTxt.setOnClickListener { view: View ->

            if (binding.signupTxtAge.text.isNullOrEmpty() || binding.signupTxtName.text.isNullOrEmpty() ||
                binding.signupTxtEmail.text.isNullOrEmpty() || binding.signupTxtPassword.text.isNullOrEmpty()
            ) {
                Toast.makeText(context, "missed input", Toast.LENGTH_SHORT).show()
            } else if (binding.signupTxtAge.text.toString().toInt() < 13) {
                Toast.makeText(context, "age is less than 13, Try again", Toast.LENGTH_SHORT).show()
            } else if (!(binding.signupTxtEmail.text.toString().matches(emailPattern.toRegex()))) {
                Toast.makeText(context, "Invalid email", Toast.LENGTH_SHORT).show()
            }
            else
            {
                    viewModel.SignUp(
                        RequestBody(
                            binding.signupTxtEmail.text.toString(),
                            binding.signupTxtPassword.text.toString(),
                            binding.signupTxtName.text.toString(),
                            binding.signupTxtAge.text.toString()

                        )
                    )
                }
            }
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
//    fun ValidateInputs(view: View):Boolean {
//        var Isvalid: Boolean = true
//        if (binding.signupTxtAge.text.isNullOrEmpty() || binding.signupTxtName.text.isNullOrEmpty() ||
//            binding.signupTxtEmail.text.isNullOrEmpty() || binding.signupTxtPassword.text.isNullOrEmpty()
//        ) {
//            Toast.makeText(context, "missed input", Toast.LENGTH_SHORT).show()
//            Isvalid = false
//        } else if (binding.signupTxtAge.text.toString().toInt() < 13) {
//            Toast.makeText(context, "age is less than 13, Try again", Toast.LENGTH_SHORT).show()
//            Isvalid = false
//        } else if (!(binding.signupTxtEmail.text.toString().matches(emailPattern.toRegex()))) {
//            Toast.makeText(context, "Invalid email", Toast.LENGTH_SHORT).show()
//            Isvalid = false
//            return Isvalid

//        } else {
//            val INTENT = Intent(this.context, UserPagesActivity::class.java)
//            startActivity(INTENT)
//        }
//        }
//    }

    /**
     * When user Click Login From Sign up with email page
     * This Alert Dialog appears to make sure that the use has an account
     * if the user already has an acount he clicks I'm Sure so he navigates to log in page
     * else user will stay in sign up page to continue
     */
    fun AlertDialog(){
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
    }

}

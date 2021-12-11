package com.example.tumbler.signupandin

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.style.UpdateLayout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.tumbler.UserPagesActivity
import com.example.tumbler.databinding.FragmentSignupWithEmailOrGoogleBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import java.lang.Exception
import androidx.core.app.ActivityCompat.startActivityForResult
import com.google.android.gms.tasks.Task
import android.R
import android.app.Activity
import android.content.ContentValues
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.ktx.Firebase


class SignupWithEmailOrGoogleFragment : Fragment() {
    lateinit var binding: FragmentSignupWithEmailOrGoogleBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignupWithEmailOrGoogleBinding.inflate(inflater, container, false)


        val mGoogleSignInClient = configureGoogleClient(requireActivity())
        binding.loginSignupSignupWithGoogleBtn.setOnClickListener { view: View ->
            val signInIntent = mGoogleSignInClient.signInIntent
            launcher.launch(signInIntent)

        }


        var SignupWithEmailAction =
            SignupWithEmailOrGoogleFragmentDirections.actionSignupWithEmailOrGoogleFragmentToSignUpFragment()
        binding.loginSignupSignupWithEmailBtn.setOnClickListener { view: View ->
            view.findNavController().navigate(SignupWithEmailAction)
        }

        return binding.root
    }


    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                try {
                    // Google Sign In was successful, authenticate with Firebase
                    val account = task.getResult(ApiException::class.java)
                    Log.d(ContentValues.TAG, "firebaseAuthWithGoogle:" + account.id)
//                    val done = firebaseAuthWithGoogle(
//                        account.idToken!!,
//                        auth,
//                        requireActivity()
//                    )

                    Toast.makeText(
                        activity?.applicationContext,
                        "email ${account.email} username ${account.displayName}",
                        Toast.LENGTH_SHORT
                    ).show()
                    // Google Sign In failed, update UI appropriately

                } catch (e: ApiException) {
                    Toast.makeText(
                        activity?.applicationContext,
                        "Error occured",
                        Toast.LENGTH_SHORT
                    ).show()

                    // Google Sign In failed, update UI appropriately
                    Log.w(ContentValues.TAG, "Google sign up failed", e)
                }
            }

        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginSignupSignupWithEmailBtn.animate().translationY(-250f).alpha(100f).duration =
            600
        binding.loginSignupSignupWithGoogleBtn.animate().translationY(-250f).alpha(100f).duration =
            600
    }


    fun configureGoogleClient(fa: Activity): GoogleSignInClient {
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        val gso: GoogleSignInOptions =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("305202642096-tg4ivg4d2ue4kqvrupccngomvfj8f6us.apps.googleusercontent.com")
                .requestEmail()
                .build()
        // Build a GoogleSignInClient with the options specified by gso
        return GoogleSignIn.getClient(fa, gso)
    }

    fun firebaseAuthWithGoogle(idToken: String, auth: FirebaseAuth, activity: Activity) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.i("Signin", "signInWithCredential:success")
                    val user = auth.currentUser
                } else {
                    // If sign in fails, display a message to the user.
                    Log.i("Signin", "signInWithCredential:failure", task.exception)
                }
            }
    }


}










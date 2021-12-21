package com.example.tumbler.signupandin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.tumbler.databinding.FragmentLoginWithEmailOrGoogleBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import android.R







class LoginWithEmailOrGoogleFragment : Fragment() {
    lateinit var binding: FragmentLoginWithEmailOrGoogleBinding

    companion object {
        const val GOOGLE_SIGN_IN = 1903
    }

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = FragmentLoginWithEmailOrGoogleBinding.inflate(inflater, container, false)

        var LoginWithEmailAction =
            LoginWithEmailOrGoogleFragmentDirections.actionLoginWithEmailOrGoogleFragmentToLoginWithEmail4Fragment()
        binding.loginSignupLoginWithEmailBtn.setOnClickListener { view: View ->
            view.findNavController().navigate(LoginWithEmailAction)
        }

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("305202642096-tg4ivg4d2ue4kqvrupccngomvfj8f6us.apps.googleusercontent.com")
            .requestEmail()
            .build()

        // Build a GoogleSignInClient with the options specified by gso.
        val mGoogleSignInClient = GoogleSignIn.getClient(requireContext(), gso)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginSignupLoginWithEmailBtn.animate().translationY(-250f).alpha(100f).duration =
            600
        binding.loginSignupLoginWithGoogleBtn.animate().translationY(-250f).alpha(100f).duration =
            600
    }

    override fun onStart() {
        super.onStart()
        // Check for existing Google Sign In account, if the user is already signed in
// the GoogleSignInAccount will be non-null.
        // Check for existing Google Sign In account, if the user is already signed in
// the GoogleSignInAccount will be non-null.
        val account = GoogleSignIn.getLastSignedInAccount(requireContext())
//        updateUI(account)
    }

    fun onClick(v: View) {
        binding.loginSignupLoginWithGoogleBtn.setOnClickListener{ view:View->

        }
    }




}

package com.example.tumbler.signupandin

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.tumbler.databinding.FragmentLoginWithEmailOrGoogleBinding
import com.example.tumbler.signupandin.LoginWithEmailOrGoogleFragment.Companion.GOOGLE_SIGN_IN
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

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

        var LoginWithEmailAction = LoginWithEmailOrGoogleFragmentDirections.actionLoginWithEmailOrGoogleFragmentToLoginWithEmail4Fragment()
        binding.loginSignupLoginWithEmailBtn.setOnClickListener { view: View ->
            view.findNavController().navigate(LoginWithEmailAction)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginSignupLoginWithEmailBtn.animate().translationY(-250f).alpha(100f).duration = 600
        binding.loginSignupLoginWithGoogleBtn.animate().translationY(-250f).alpha(100f).duration = 600
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        googleSignInClient = GoogleSignIn.getClient(requireContext(), getGSO())
        binding.loginSignupLoginWithGoogleBtn.setOnClickListener { signIn() }
    }
    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
       // startActivityForResult(signInIntent, SignupWithEmailOrGoogleFragment.GOOGLE_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == SignupWithEmailOrGoogleFragment.GOOGLE_SIGN_IN) {
//            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
//            try {
//                val account = task.getResult(ApiException::class.java)!!
//                firebaseAuthWithGoogle(account.idToken!!)
//            } catch (e: ApiException) {
//                //handle error
//            }
//        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    //handle success
                } else {
                    //handle error
                }
            }
    }

    private fun getGSO(): GoogleSignInOptions {
        return  GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("305202642096-tg4ivg4d2ue4kqvrupccngomvfj8f6us.apps.googleusercontent.com")
            .requestEmail()
            .build()
    }

}

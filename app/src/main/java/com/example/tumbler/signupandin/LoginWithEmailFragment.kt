package com.example.tumbler.signupandin
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tumbler.databinding.FragmentLoginWithEmailBinding

class LoginWithEmailFragment : Fragment() {
    lateinit var binding: FragmentLoginWithEmailBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginWithEmailBinding.inflate(inflater, container, false)
        return binding.root
    }
}

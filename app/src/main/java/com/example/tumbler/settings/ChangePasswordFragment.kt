package com.example.tumbler.settings

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.tumbler.databinding.FragmentChangePasswordBinding
import com.example.tumbler.model.entity.settings.change_password
import com.example.tumbler.userprofile.SettingsViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel


class ChangePasswordFragment : Fragment() {
    lateinit var binding:FragmentChangePasswordBinding
    var myshared: SharedPreferences? = null
    private val viewModel: SettingsViewModel by sharedViewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentChangePasswordBinding.inflate(inflater,container,false)
        MyObserver()
        CheckAndNavigate()
        return binding.root
    }

    fun MyObserver() {
        viewModel.changePasswordLiveData
            .observe(
                viewLifecycleOwner,
                androidx.lifecycle.Observer {
                    if(it.status.toInt()==200) {
                        Toast.makeText(requireContext(), it.msg.toString(), Toast.LENGTH_SHORT).show()
                    }

                }
            )
    }

    fun CheckAndNavigate() {
        var currentPassword = binding.currentPassword.text
        var newPassword = binding.newPassword.text
        var confirmPassword = binding.confirmPassword.text
        binding.doneBtn.setOnClickListener {
            viewModel.ChangePassword(change_password(currentPassword.toString(),newPassword.toString(),confirmPassword.toString()))

        }
    }
}
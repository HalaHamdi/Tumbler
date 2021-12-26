package com.example.tumbler.userprofile

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.tumbler.CreateNewTumblrViewModel
import com.example.tumbler.MainActivity
import com.example.tumbler.R
import com.example.tumbler.UserPagesActivity
import com.example.tumbler.databinding.FragmentSettingsBinding
import com.example.tumbler.model.entity.createNewTumblr.CreateBlogRequest
import org.koin.android.viewmodel.ext.android.sharedViewModel


class SettingsFragment : Fragment() {
    var myshared: SharedPreferences?=null
    private val viewModel: SettingsViewModel by sharedViewModel()
    lateinit var binding: FragmentSettingsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentSettingsBinding.inflate(inflater,container,false)
        MyObserver()
        CheckAndNavigate()
        return binding.root
    }

    fun MyObserver() {
        viewModel.logoutLiveData.
        observe(viewLifecycleOwner,
            Observer {
            if(it.status.toInt()==200) {
                myshared = getActivity()?.getSharedPreferences("myshared",0)
                myshared?.edit()!!.clear().commit()
                val INTENT = Intent(this.context, MainActivity::class.java)
                startActivity(INTENT)
            }
            else
                Toast.makeText(requireContext(), it.msg, Toast.LENGTH_SHORT).show()
        })
        }




    fun CheckAndNavigate() {
        binding.logoutButton.setOnClickListener {

                viewModel.UserLogout()
            }
        }
    }



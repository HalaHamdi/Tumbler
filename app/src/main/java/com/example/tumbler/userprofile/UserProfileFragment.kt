package com.example.tumbler.userprofile

import android.content.Context
import android.icu.text.Transliterator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.get
import androidx.databinding.adapters.AdapterViewBindingAdapter
import androidx.databinding.adapters.AdapterViewBindingAdapter.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation.createNavigateOnClickListener
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.tumbler.BaseApplication
import com.example.tumbler.CreateNewTumblrViewModel
import com.example.tumbler.databinding.FragmentUserProfileBinding
import org.koin.android.viewmodel.ext.android.sharedViewModel

class UserProfileFragment : Fragment() {
    lateinit var binding: FragmentUserProfileBinding
    private val myContext = FragmentActivity()
    var UserBlogs= arrayOf("Elonsol","Neboo","Hala","Create a new Tumblr")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserProfileBinding.inflate(inflater, container, false)
        binding.textView2.text=BaseApplication.user.blog_username
        val UserBlogsAdapter=ArrayAdapter<String>(requireContext(),android.R.layout.simple_spinner_dropdown_item,UserBlogs)
        binding.userBlogsSpinner.adapter=UserBlogsAdapter
        var p2s=false
        binding.userBlogsSpinner.onItemSelectedListener= object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if(p2==3 )
                {
                    p1!!.findNavController().navigate(UserProfileFragmentDirections.actionUserProfileFragmentToCreateNewTumblrFragment())
                }

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }


        }
        var action=UserProfileFragmentDirections.actionUserProfileFragmentToSettingsFragment()
        binding.settingsIcon.setOnClickListener { view: View ->
            view.findNavController().navigate(action)
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configureTopNavigation()
    }

    private fun configureTopNavigation() {
        binding.viewPager.adapter = ViewPagerAdapter(childFragmentManager, 3)
        binding.viewPager.offscreenPageLimit = 3
        binding.tabs.setupWithViewPager(binding.viewPager)
    }
}

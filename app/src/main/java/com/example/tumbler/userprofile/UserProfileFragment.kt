package com.example.tumbler.userprofile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.get
import androidx.databinding.adapters.AdapterViewBindingAdapter.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.tumbler.BaseApplication
import com.example.tumbler.UserPagesActivity
import com.example.tumbler.databinding.FragmentUserProfileBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.android.viewmodel.ext.android.sharedViewModel


class UserProfileFragment : Fragment() {
    lateinit var binding: FragmentUserProfileBinding
    private val myContext = FragmentActivity()
    var UserBlogs = arrayOf("Elonsol", "Neboo", "Hala", "Create a new Tumblr")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserProfileBinding.inflate(inflater, container, false)

        // binding.profilePic.setImageURI(BaseApplication.user.blog_avatar as Uri)
        val UserBlogsAdapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_dropdown_item, UserBlogs)
        binding.userBlogsSpinner.adapter = UserBlogsAdapter
        var p2s = false
        binding.userBlogsSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (p2 == 3) {
                    p1!!.findNavController().navigate(UserProfileFragmentDirections.actionUserProfileFragmentToCreateNewTumblrFragment())
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
        var action = UserProfileFragmentDirections.actionUserProfileFragmentToSettingsFragment()
        binding.settingsIcon.setOnClickListener { view: View ->
            view.findNavController().navigate(action)
        }

//        val fab = (requireActivity() as UserPagesActivity).binding.createPostButton
//        fab.visibility = View.VISIBLE
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textView2.text = BaseApplication.user.blog_username
        FollowingAdapter.DownloadImageFromInternet(binding.profilePic).execute(BaseApplication.user.blog_avatar)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configureTopNavigation()
    }
    /**
     * Function to make the Bottom Navigation visible OnResume
     */
    override fun onResume() {
        super.onResume()
        val navBar: BottomNavigationView = (requireActivity() as UserPagesActivity).binding.footerNavigation
        val fab = (requireActivity() as UserPagesActivity).binding.createPostButton
        fab.visibility = View.VISIBLE
        navBar.visibility = View.VISIBLE
    }

    private fun configureTopNavigation() {
        binding.viewPager.adapter = ViewPagerAdapter(childFragmentManager, 3)
        binding.viewPager.offscreenPageLimit = 3
        binding.tabs.setupWithViewPager(binding.viewPager)
    }
}

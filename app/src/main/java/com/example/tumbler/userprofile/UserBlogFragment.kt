package com.example.tumbler.userprofile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.tumbler.R
import com.example.tumbler.databinding.FragmentUserBlogBinding
import org.koin.android.viewmodel.ext.android.sharedViewModel

class UserBlogFragment : Fragment() {
   lateinit var binding : FragmentUserBlogBinding
    private val viewModel: UserBlogViewModel by sharedViewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentUserBlogBinding.inflate(inflater,container,false)
        binding.chatIcon.setOnClickListener{view:View->
            view.findNavController().navigate(UserBlogFragmentDirections.actionUserBlogFragmentToChatFragment())
        }
        val args = UserBlogFragmentArgs.fromBundle(requireArguments())
        val adapter = BlogProfilePostsAdapter(viewModel)
        binding.textView2.text=args.userName
      binding.rvUserBlogPosts.adapter = adapter
        viewModel.getsubmittedPosts(args.userID)
        viewModel.userBlogLiveData.observe(
            viewLifecycleOwner,
            Observer {
                it?.let {
                    adapter.setList(it)
                }
            }
        )
        return binding.root
    }
}
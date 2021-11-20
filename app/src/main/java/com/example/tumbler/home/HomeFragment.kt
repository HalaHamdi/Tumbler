package com.example.tumbler.home

import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tumbler.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    //lateinit var binding: FragmentHomeBinding;
    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    lateinit var binding:FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getPostByID(5)
        viewModel.getPostNotesByID(5)
        viewModel.getUsersList()
        viewModel.gett()

        viewModel.usersListMutableLiveData.observe(
            viewLifecycleOwner,
            Observer {
                if (!it.isNullOrEmpty()) {
                    binding.userNamePost.text = it[0].title
                }
            }
        )


        viewModel.postByIDMutableLiveData.observe(viewLifecycleOwner, Observer {
            if(it != null){
                binding.userNamePost.text = it.postByIDResponse.blogUsername
                //binding.postContent.text = it.postByIDResponse.postBody
                binding.postContent.setText(Html.fromHtml(it.postByIDResponse.postBody))

            }
        })

        viewModel.postNotesByIDMutableLiveData.observe(viewLifecycleOwner, Observer {
            if(it != null){
                binding.postNumNotes.text = it.postNotesByIDResponse.replies.size.toString()
            }
        })

    }
}

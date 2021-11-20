package com.example.tumbler.home

import com.example.tumbler.R
import android.os.Bundle
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

        viewModel.getUsersList()

        viewModel.usersListMutableLiveData.observe(viewLifecycleOwner, Observer {
            if(!it.isNullOrEmpty()){
                binding.userNamePost.text = it[0].title
            }
        })
    }
}

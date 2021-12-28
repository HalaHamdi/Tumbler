package com.example.tumbler

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.tumbler.databinding.FragmentCreateNewTumblrBinding
import com.example.tumbler.model.entity.createNewTumblr.CreateBlogRequest
import org.koin.android.viewmodel.ext.android.sharedViewModel
import java.util.Observer

class CreateNewTumblrFragment : Fragment() {
    private val viewModel: CreateNewTumblrViewModel by sharedViewModel()
    lateinit var binding: FragmentCreateNewTumblrBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCreateNewTumblrBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MyObserver()
        CheckAndNavigate()
    }

    fun MyObserver() {
        viewModel.createNewTumblrLiveData.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer {
                Log.i("E1", it.status.toString())
                if (it.status.toInt() == 200) {
                    Log.i("Elonsoool", "MyObserver:status 200 ")
                    Toast.makeText(requireContext(), it.msg, Toast.LENGTH_SHORT).show()
                } else
                    Toast.makeText(requireContext(), "Enter a new Username", Toast.LENGTH_SHORT).show()
            }
        )
    }

    fun CheckAndNavigate() {
        binding.Save.setOnClickListener {
            Log.i("Mgz1", "CheckAndNavigate: ")
            if (binding.blogUsernameTxt.text.isNullOrEmpty()) {
                Toast.makeText(context, "Please enter the blog username", Toast.LENGTH_SHORT).show()
            } else {

                val A = CreateBlogRequest(binding.blogUsernameTxt.text.toString(), binding.blogTitleTxt.text.toString())
                viewModel.CreateNewTumblr(A)
            }
        }
    }
}

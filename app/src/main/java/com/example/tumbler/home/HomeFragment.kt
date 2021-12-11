package com.example.tumbler.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.tumbler.databinding.FragmentHomeBinding
import org.koin.android.viewmodel.ext.android.sharedViewModel

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by sharedViewModel()
    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val adapter = PostAdapter()
        binding.postList.adapter = adapter

        viewModel.getRandomPosts()

        viewModel.postsLiveData.observe(
            viewLifecycleOwner,
            Observer {
                it?.let {
                    // adapter.postList = it
                    adapter.setlist(it)
                }
            }
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        viewModel.postNotesByIDMutableLiveData.observe(
//            viewLifecycleOwner,
//            Observer {
//                if (it != null) {
//                    binding.postNumNotes.text = it.postNotesByIDResponse.replies.size.toString()
//                }
//            }
//        )
    }
}

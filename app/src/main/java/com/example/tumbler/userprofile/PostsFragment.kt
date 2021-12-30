package com.example.tumbler.userprofile
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.tumbler.databinding.FragmentPostsBinding
import org.koin.android.viewmodel.ext.android.sharedViewModel

class PostsFragment : Fragment() {
    lateinit var binding: FragmentPostsBinding
    private val viewModel: FollowingViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPostsBinding.inflate(inflater, container, false)
        val adapter = PostsAdapter(viewModel)
        binding.rvPosts.adapter = adapter
        viewModel.getsubmittedPosts()
        viewModel.submittedPostsLiveData.observe(
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

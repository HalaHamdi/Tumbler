package com.example.tumbler.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.tumbler.UserPagesActivity
import com.example.tumbler.databinding.FragmentPostNotesBinding
import com.example.tumbler.model.entity.dashboard.RepliesPage
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.android.viewmodel.ext.android.sharedViewModel

/**
 * Notes page fragment class
 */
class PostNotesFragment : Fragment() {
    lateinit var binding: FragmentPostNotesBinding
    private val viewModel: HomeViewModel by sharedViewModel()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPostNotes()
    }

    /**
     * function to be called when notes fragment is being created:
     * setting up binding object, adapter of notes likes, and setting live data listeners
     * it also removes navigation bar and floating button
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPostNotesBinding.inflate(inflater, container, false)
        binding.likesTxt.setOnClickListener { view: View ->
            view.findNavController().navigate(PostNotesFragmentDirections.actionPostNotesFragmentToNotesLikesFragment())
        }
        binding.reblogsTxt.setOnClickListener { view: View ->
            view.findNavController().navigate(PostNotesFragmentDirections.actionPostNotesFragmentToNotesReblogsFragment())
        }

        val adapter = NotesRepliesAdapter(viewModel)
        binding.rvNotes.adapter = adapter

        viewModel.postRepliesLiveData.observe(
            viewLifecycleOwner,
            Observer {
                it?.let {
                    adapter.setlist(it as ArrayList<RepliesPage>)
                }
            }
        )

        viewModel.curPostLikesNum.observe(
            viewLifecycleOwner,
            Observer {
                binding.likesNumberTxt.text = it!!.toString()
            }
        )

        viewModel.curPostReblogsNum.observe(
            viewLifecycleOwner,
            Observer {
                binding.reblogsNumberTxt.text = it!!.toString()
            }
        )

        binding.replyBtn.setOnClickListener {
            if (binding.notesTxtField.text.isNullOrEmpty()) {
                Toast.makeText(context, "Write Somthing to comment", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.addComment(binding.notesTxtField.text.toString())
                Toast.makeText(context, "Comment Added Successfully", Toast.LENGTH_SHORT).show()
                binding.notesTxtField.text.clear()
                viewModel.getPostNotes()
            }
        }
        val navBar: BottomNavigationView = (requireActivity() as UserPagesActivity).binding.footerNavigation
        val fab = (requireActivity() as UserPagesActivity).binding.createPostButton
        fab.visibility = View.GONE
        navBar.visibility = View.GONE

        return binding.root
    }

    /**
     * function to be called when notes page is being destroyed:
     * re show navigation bar and floating button
     */
    override fun onDestroy() {
        super.onDestroy()
        val navBar: BottomNavigationView = (requireActivity() as UserPagesActivity).binding.footerNavigation
        val fab = (requireActivity() as UserPagesActivity).binding.createPostButton
        fab.visibility = View.VISIBLE
        navBar.visibility = View.VISIBLE
    }
}

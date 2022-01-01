package com.example.tumbler.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.tumbler.UserPagesActivity
import com.example.tumbler.databinding.FragmentNotesReblogsBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.android.viewmodel.ext.android.sharedViewModel

/**
 * Notes reblogs page fragment class
 */
class NotesReblogsFragment : Fragment() {
    lateinit var binding: FragmentNotesReblogsBinding
    private val viewModel: HomeViewModel by sharedViewModel()

    /**
     * function to be called when notes reblogs fragment is being created:
     * setting up binding object, adapter of notes likes, and setting live data listeners
     * it also removes navigation bar and floating button
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNotesReblogsBinding.inflate(inflater, container, false)

        val adapter = LikesReblogAdapter(viewModel)
        binding.reblogsRv.adapter = adapter

        viewModel.curPostReblogs.observe(
            viewLifecycleOwner,
            Observer {
                it?.let {
                    val x: List<Pair<String, String>> = List<Pair<String, String>>(it.size) { it2 ->
                        Pair(it[it2].blogUsername, it[it2].blogAvatar)
                    }
                    adapter.setlist(x as ArrayList<Pair<String, String>>)
                }
            }
        )
        val navBar: BottomNavigationView = (requireActivity() as UserPagesActivity).binding.footerNavigation
        val fab = (requireActivity() as UserPagesActivity).binding.createPostButton
        fab.visibility = View.GONE
        navBar.visibility = View.GONE

        return binding.root
    }
    /**
     * function to be called when notes reblogs page  is already created:
     * call ViewModel to get reblogs list
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getReblogsList()
    }
}

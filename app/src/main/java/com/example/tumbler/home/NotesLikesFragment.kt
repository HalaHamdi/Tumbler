package com.example.tumbler.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.tumbler.databinding.FragmentNotesLikesBinding
import com.example.tumbler.databinding.FragmentPostNotesBinding
import com.example.tumbler.model.entity.dashboard.RepliesPage
import org.koin.android.viewmodel.ext.android.sharedViewModel

class NotesLikesFragment : Fragment() {

    lateinit var binding: FragmentNotesLikesBinding
    private val viewModel: HomeViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNotesLikesBinding.inflate(inflater, container, false)

        val adapter = LikesReblogAdapter(viewModel)

        binding.likesRv.adapter = adapter

        viewModel.curPostLikes.observe(viewLifecycleOwner, Observer {
            it?.let{
                val x: List<Pair<String,String>> = List<Pair<String,String>>(it.size){ it2 ->
                    Pair(it[it2].blogUsername,it[it2].blogAvatar)
                }
                adapter.setlist(x as ArrayList<Pair<String, String>>)
            }
        })


        return binding.root







    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLikesList()

    }
}

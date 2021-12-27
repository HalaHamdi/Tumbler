package com.example.tumbler.home

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.tumbler.BaseApplication
import com.example.tumbler.R
import com.example.tumbler.databinding.FragmentHomeBinding
import com.example.tumbler.model.entity.dashboard.DashboardPost
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.sharedViewModel

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by sharedViewModel()
    lateinit var binding: FragmentHomeBinding

    //val sharedPreferences: SharedPreferences by inject()

    //lateinit var token:String
    //((MyApplication) this.getApplication()).getSomeVariable();

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //Log.i("Nebo",token)
        // Inflate the layout for this fragment
        //token = (activity?.applicationContext as BaseApplication).user.id.toString()
        //Log.i("Nebo",token)
        binding = FragmentHomeBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val adapter = PostAdapter(viewModel)
        binding.postList.adapter = adapter

        //viewModel.getRandomPosts()

//        viewModel.postsLiveData.observe(
//            viewLifecycleOwner,
//            Observer {
//                it?.let {
//                    // adapter.postList = it
//                    adapter.setlist(it)
//                }
//            }
//        )

        viewModel.dashhboardPostsMutableLiveData.observe(
            viewLifecycleOwner,
            Observer {
                it?.let {
                    adapter.setlist(it as ArrayList<DashboardPost>)
                }
            }
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getDashboard()

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

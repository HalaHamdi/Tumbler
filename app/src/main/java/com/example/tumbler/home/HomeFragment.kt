package com.example.tumbler.home

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.tumbler.UserPagesActivity
import com.example.tumbler.databinding.FragmentHomeBinding
import com.example.tumbler.model.entity.dashboard.DashboardPost
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.android.viewmodel.ext.android.sharedViewModel

/**
 * home page fragment class
 */
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by sharedViewModel()
    lateinit var binding: FragmentHomeBinding

    // val sharedPreferences: SharedPreferences by inject()

    // lateinit var token:String
    // ((MyApplication) this.getApplication()).getSomeVariable();

    /**
     * function to be called when home fragment is being created:
     * setting up binding object, adapter of home posts, and setting live data listeners
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Log.i("Nebo",token)
        // Inflate the layout for this fragment
        // token = (activity?.applicationContext as BaseApplication).user.id.toString()
        // Log.i("Nebo",token)
        binding = FragmentHomeBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val adapter = PostAdapter(viewModel)
        binding.postList.adapter = adapter
        // viewModel.getRandomPosts()

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
//        val fab = (requireActivity() as UserPagesActivity).binding.createPostButton
//        fab.visibility = View.VISIBLE
        return binding.root
    }

    /**
     * function to be called when user switch from home page -> clear home posts
     */
    override fun onStop() {
        super.onStop()
        Log.i("DashboardBug", "Abbas")

        (binding.postList.adapter as PostAdapter).clear()
    }

    /**
     * function to be called when home page is already created:
     * call ViewModel to fetch Dashboard posts
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("Dashboard Bug", "onviewcreated")
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

    /**
     * function to be called when user switch to home page:
     * setting up navigation bar and create post floating button
     */
    override fun onResume() {
        super.onResume()
        val navBar: BottomNavigationView = (requireActivity() as UserPagesActivity).binding.footerNavigation
        val fab = (requireActivity() as UserPagesActivity).binding.createPostButton
        fab.visibility = View.VISIBLE
        navBar.visibility = View.VISIBLE
    }
}

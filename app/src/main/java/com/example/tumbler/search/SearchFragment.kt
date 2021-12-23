package com.example.tumbler.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tumbler.databinding.FragmentSearchBinding
import org.koin.android.viewmodel.ext.android.sharedViewModel

class SearchFragment : Fragment() {

    private val viewModel:SearchViewModel by sharedViewModel()
    lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("Hala","In the search Fragment")

        binding=FragmentSearchBinding.inflate(inflater)
        Log.i("Hala","After Binding")

        binding.lifecycleOwner = this
        Log.i("Hala","After Life cycle owner ")

        //for data binding
        binding.viewModel= this.viewModel
        Log.i("Hala","After  data binding")


        val adapter =RecommendedBlogsAdapter(viewModel)
        binding.rvRecommendedBlogs.adapter=adapter
        Log.i("Hala","After Adapter")

        viewModel.getRecommendedBlogs()
        Log.i("Hala","After recommended blogs")


        //for UI modification
        viewModel.blogsLiveData.observe(
            viewLifecycleOwner, Observer { it?.let {
                adapter.setList(it)
            } }
        )

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}

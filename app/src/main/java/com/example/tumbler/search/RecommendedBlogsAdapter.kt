package com.example.tumbler.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tumbler.databinding.RecommendedBlogsListItemBinding
import com.example.tumbler.model.entity.search.Blogs


class RecommendedBlogsAdapter(val viewModel: SearchViewModel):RecyclerView.Adapter<RecommendedBlogsAdapter.RecommendedBlogsViewHolder>() {

    var blogsList = listOf<Blogs>()

    override fun getItemCount(): Int =blogsList.count()

    fun setList( allBlogs:List<Blogs>){
        this.blogsList=allBlogs
        notifyDataSetChanged()

    }

    class RecommendedBlogsViewHolder(val binding:RecommendedBlogsListItemBinding) : RecyclerView.ViewHolder(binding.root) {


        fun bind(blog:Blogs){
        binding.blogName.text=blog.username
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendedBlogsViewHolder{
        var inflater= LayoutInflater.from(parent.context)
        val binding=RecommendedBlogsListItemBinding.inflate(inflater, parent, false)
        return RecommendedBlogsViewHolder(binding)
    }


    override fun onBindViewHolder(holder: RecommendedBlogsViewHolder, position: Int) {
        var recommendedBlog:Blogs=blogsList.get(position)
        holder.bind(recommendedBlog)
    }

}
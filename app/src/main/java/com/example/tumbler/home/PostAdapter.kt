package com.example.tumbler.home

import android.annotation.SuppressLint
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tumbler.R
import com.example.tumbler.databinding.PostItemBinding
import com.example.tumbler.model.entity.Post

class PostAdapter : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    var postList = listOf<Post>()

    fun setlist(pstList: List<Post>){
        this.postList = pstList
        notifyDataSetChanged()
    }

    override fun getItemCount() = postList.size


    class PostViewHolder(val binding: PostItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(post : Post){
            binding.postContent.text = Html.fromHtml(post.body)
            binding.userNamePost.text = post.user
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        var inflater  = LayoutInflater.from(parent.context)
        val binding = PostItemBinding.inflate(inflater,parent,false)
        return PostViewHolder(binding)
//            .inflate(R.layout.post_item,parent,false))
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        var post :Post = postList.get(position)
        holder.bind(post)
    }

}
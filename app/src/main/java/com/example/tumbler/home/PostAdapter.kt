package com.example.tumbler.home

import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tumbler.databinding.PostItemBinding
import com.example.tumbler.di.viewModelModule
import com.example.tumbler.model.entity.Post
import com.example.tumbler.model.entity.dashboard.DashboardPost
import org.koin.android.viewmodel.ext.android.sharedViewModel


class PostAdapter(val viewModel:HomeViewModel) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    var postList = listOf<DashboardPost>()

    //private val viewModel: HomeViewModel by sharedViewModel()


    fun setlist(pstList: List<DashboardPost>) {
        this.postList = pstList
        notifyDataSetChanged()
    }

    override fun getItemCount() = postList.size

    class PostViewHolder(val binding: PostItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(post: DashboardPost) {
            binding.postContent.text = Html.fromHtml(post.post_body)
            binding.userNamePost.text = post.blog_username
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        val binding = PostItemBinding.inflate(inflater, parent, false)
        return PostViewHolder(binding)
//            .inflate(R.layout.post_item,parent,false))
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        var post: DashboardPost = postList.get(position)
        holder.bind(post)
        holder.binding.postLoveIcon.setOnClickListener {
//            viewModel.LikePost()
        }

    }
}

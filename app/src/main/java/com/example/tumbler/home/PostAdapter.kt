package com.example.tumbler.home

import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tumbler.BaseApplication
import com.example.tumbler.databinding.PostItemBinding
import com.example.tumbler.di.viewModelModule
import com.example.tumbler.model.entity.Post
import com.example.tumbler.model.entity.dashboard.DashboardPost
import org.koin.android.viewmodel.ext.android.sharedViewModel
import android.graphics.drawable.Drawable

import com.example.tumbler.R

import android.widget.ImageView
import java.io.InputStream
import java.net.URL


class PostAdapter(val viewModel:HomeViewModel) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    var postList = ArrayList<DashboardPost>()
    var maxReachedPosts :Int = 0

    //private val viewModel: HomeViewModel by sharedViewModel()


    fun setlist(pstList: ArrayList<DashboardPost>) {
        this.postList.addAll(pstList)

        notifyDataSetChanged()
    }

    override fun getItemCount() = postList.size

    class PostViewHolder(val binding: PostItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(post: DashboardPost) {
            binding.postContent.text = Html.fromHtml(post.post_body)
            binding.userNamePost.text = post.blog_username
            binding.postNumNotes.text = post.numNotes.toString()
//            binding.profilePhotoPost.setImageResource(Drawable.createFromStream( URL(post.blog_avatar).content as InputStream, "src name"))


            if(post.isLiked)
                binding.postLoveIcon.setImageResource(R.drawable.ic_baseline_love_red_button)
            else
                binding.postLoveIcon.setImageResource(R.drawable.ic_baseline_love_button)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        val binding = PostItemBinding.inflate(inflater, parent, false)
        return PostViewHolder(binding)
//            .inflate(R.layout.post_item,parent,false))
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        var post: DashboardPost = postList[position]
        //if(position)
        holder.binding.postLoveIcon.setOnClickListener {
            //Log.i("Like",position.toString())
            if(post.isLiked){
                viewModel.UnLikePost(post.post_id,BaseApplication.user.blog_id)
                post.numNotes--
                post.isLiked = false
            }
            else {
                viewModel.LikePost(post.post_id, BaseApplication.user.blog_id)
                post.numNotes++
                post.isLiked = true
            }
            notifyDataSetChanged()
        }
        //Log.i("TTT","adapter " + viewModel.dashhboardPostsisLikedMutableLiveData.value.toString())
        holder.bind(post)
    }
}

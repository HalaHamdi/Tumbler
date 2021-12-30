package com.example.tumbler.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.tumbler.BaseApplication
import com.example.tumbler.R
import com.example.tumbler.databinding.PostItemBinding
import com.example.tumbler.model.entity.dashboard.DashboardPost
import com.example.tumbler.userprofile.FollowingAdapter

class PostAdapter(val viewModel: HomeViewModel) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    var postList = ArrayList<DashboardPost>()
    val maxReachedPosts: Int get() = postList.size

    // private val viewModel: HomeViewModel by sharedViewModel()
    public fun clear() {
        postList.clear()
        notifyDataSetChanged()
    }
    fun setlist(pstList: ArrayList<DashboardPost>) {
        this.postList.addAll(pstList)

        notifyDataSetChanged()
    }

    override fun getItemCount() = postList.size

    class PostViewHolder(val binding: PostItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(post: DashboardPost) {
            binding.postContent.loadData("<style>img{display: inline;height: auto;max-width: 100%;}</style>" + post.post_body, "text/html", "UTF-8")
            // binding.postContent.text = Html.fromHtml(post.post_body)
            binding.userNamePost.text = post.blog_username
            binding.postNumNotes.text = post.numNotes.toString()
            if (post.isLiked)
                binding.postLoveIcon.setImageResource(R.drawable.ic_baseline_love_red_button)
            else
                binding.postLoveIcon.setImageResource(R.drawable.ic_baseline_love_button)
            FollowingAdapter.DownloadImageFromInternet(binding.profilePhotoPost).execute(post.blog_avatar)
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

        if (position == maxReachedPosts - 2) {
            viewModel.updateDashboard()
        }
        var userID:Int = post.blog_id
        holder.binding.profilePhotoPost.setOnClickListener{view:View->
            view.findNavController().navigate((HomeFragmentDirections.actionHomeFragmentToUserBlogFragment(userID, post.blog_username)))
        }

        holder.binding.postNumNotes.setOnClickListener { view: View ->
            viewModel.currentPost = post.post_id
            view.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToPostNotesFragment())
        }
        holder.binding.postCommentIcon.setOnClickListener { view: View ->
            viewModel.currentPost = post.post_id
            view.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToPostNotesFragment())
        }

        holder.binding.postLoveIcon.setOnClickListener {
            LoveClickListner(post)
        }

//        holder.binding.profilePhotoPost.setOnClickListener { view: View ->
//            navigatetoUser(post, view)
//        }
        holder.binding.userNamePost.setOnClickListener { view: View ->
            navigatetoUser(post, view)
        }

        holder.bind(post)
    }

    fun navigatetoUser(post: DashboardPost, view: View) {
        // view.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToPostNotesFragment())
    }

    fun LoveClickListner(post: DashboardPost) {
        // Log.i("Like",position.toString())
        if (post.isLiked) {
            viewModel.UnLikePost(post.post_id, BaseApplication.user.blog_id)
            post.numNotes--
            post.isLiked = false
        } else {
            viewModel.LikePost(post.post_id, BaseApplication.user.blog_id)
            post.numNotes++
            post.isLiked = true
        }
        notifyDataSetChanged()
    }
}

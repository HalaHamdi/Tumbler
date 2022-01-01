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

/**
 * Adapter for recycler view that shows dashboard posts
 * @property[viewModel] View Model of home page passed to call its functions to deal with remote repository when user takes action
 */
class PostAdapter(val viewModel: HomeViewModel) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    var postList = ArrayList<DashboardPost>()
    val maxReachedPosts: Int get() = postList.size

    /**
     * function to clear posts list
     */
    public fun clear() {
        postList.clear()
        notifyDataSetChanged()
    }

    /**
     * function to update recycler view list and notify the UI that list has changed
     * @param[ntsList] array of posts to add at end of the list
     */
    fun setlist(pstList: ArrayList<DashboardPost>) {
        this.postList.addAll(pstList)

        notifyDataSetChanged()
    }

    /**
     * function to get maximum number of items in the list
     */
    override fun getItemCount() = postList.size

    /**
     * holder class for single item view-> it handles updating the UI
     * @property[binding] binding object of single item to deal with UI
     */
    class PostViewHolder(val binding: PostItemBinding) : RecyclerView.ViewHolder(binding.root) {

        /**
         * function that updates ui of single item (user info in this adapter) according to passed object
         * @param[likeReblog] dashboard post that should be shown in UI
         */
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

    /**
     * function to be called when making new view to make holder for it
     * it sets binding object and inflate the layer
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        val binding = PostItemBinding.inflate(inflater, parent, false)
        return PostViewHolder(binding)
//            .inflate(R.layout.post_item,parent,false))
    }

    /**
     * function to be called whenever an item is popping to screen-> it gets the item and updates the UI
     * it also sets click listners for post buttons (love, comment, etc..)
     */
    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {

        var post: DashboardPost = postList.get(position)

        if (position == maxReachedPosts - 2) {
            viewModel.updateDashboard()
        }
        var userID: Int = post.blog_id
        holder.binding.profilePhotoPost.setOnClickListener { view: View ->
            view.findNavController().navigate((HomeFragmentDirections.actionHomeFragmentToUserBlogFragment(userID, post.blog_username, post.blog_avatar)))
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

    /**
     * function to be executed when users press love button. Notify remote repository and update UI
     * @param[post] the post that users like
     */
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

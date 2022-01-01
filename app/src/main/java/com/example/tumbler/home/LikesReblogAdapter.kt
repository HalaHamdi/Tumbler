package com.example.tumbler.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tumbler.databinding.ReblogsLikesItemBinding
import com.example.tumbler.userprofile.FollowingAdapter

/**
 * Adapter for recycler view that shows users who liked or rebloged a post
 * @property[viewModel] View Model of home page passed to call its functions to deal with remote repository when user takes action
 */
class LikesReblogAdapter(val viewModel: HomeViewModel) : RecyclerView.Adapter<LikesReblogAdapter.LikesReblogsViewHolder>() {
    var likesReblogList = ArrayList<Pair<String, String>>()

    /**
     * function to update recycler view list and notify the UI that list has changed
     * @param[ntsList] array of users info to add at end of the list
     */
    fun setlist(ntsList: ArrayList<Pair<String, String>>) {
        this.likesReblogList.addAll(ntsList)
        notifyDataSetChanged()
    }

    /**
     * function to get maximum number of items in the list
     */
    override fun getItemCount() = likesReblogList.size

    /**
     * holder class for single item view-> it handles updating the UI
     * @property[binding] binding object of single item to deal with UI
     */
    class LikesReblogsViewHolder(val binding: ReblogsLikesItemBinding) : RecyclerView.ViewHolder(binding.root) {

        /**
         * function that updates ui of sigle item (user info in this adapter) according to passed object
         * @param[likeReblog] user info that should be shown in UI
         */
        fun bind(likeReblog: Pair<String, String>) {
            binding.username.text = likeReblog.first
            FollowingAdapter.DownloadImageFromInternet(binding.userImage).execute(likeReblog.second)
        }
    }

    /**
     * function to be called when making new view to make holder for it
     * it sets binding object and inflate the layer
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikesReblogsViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        val binding = ReblogsLikesItemBinding.inflate(inflater, parent, false)
        return LikesReblogsViewHolder(binding)
    }

    /**
     * function to be called whenever an item is popping to screen-> it get the item and updates the UI
     */
    override fun onBindViewHolder(holder: LikesReblogsViewHolder, position: Int) {
        var note: Pair<String, String> = likesReblogList.get(position)
        holder.bind(note)
    }
}

package com.example.tumbler.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tumbler.databinding.ReblogsLikesItemBinding
import com.example.tumbler.userprofile.FollowingAdapter

class LikesReblogAdapter(val viewModel: HomeViewModel) : RecyclerView.Adapter<LikesReblogAdapter.LikesReblogsViewHolder>() {
    var likesReblogList = ArrayList<Pair<String, String>>()

    fun setlist(ntsList: ArrayList<Pair<String, String>>) {
        this.likesReblogList.addAll(ntsList)
        notifyDataSetChanged()
    }

    override fun getItemCount() = likesReblogList.size

    class LikesReblogsViewHolder(val binding: ReblogsLikesItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(likeReblog: Pair<String, String>) {
            binding.username.text = likeReblog.first
            FollowingAdapter.DownloadImageFromInternet(binding.userImage).execute(likeReblog.second)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikesReblogsViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        val binding = ReblogsLikesItemBinding.inflate(inflater, parent, false)
        return LikesReblogsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LikesReblogsViewHolder, position: Int) {
        var note: Pair<String, String> = likesReblogList.get(position)
        holder.bind(note)
    }
}

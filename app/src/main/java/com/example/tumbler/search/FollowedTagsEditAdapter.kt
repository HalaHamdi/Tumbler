package com.example.tumbler.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tumbler.databinding.TagsFollowedEditListItemBinding
import com.example.tumbler.model.entity.search.UserTags

class FollowedTagsEditAdapter(val viewModel: FollowedTagsEditViewModel) : RecyclerView.Adapter<FollowedTagsEditAdapter.FollowedTagsEditViewHolder>() {

    var userTagList = listOf<UserTags>()

    override fun getItemCount() = userTagList.count()

    fun setList(userTags: List<UserTags>) {
        this.userTagList = userTags
        notifyDataSetChanged()
    }

    class FollowedTagsEditViewHolder(val binding: TagsFollowedEditListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(userTags: UserTags) {
            binding.tagFollowedName.text = "#${userTags.tagDescription}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowedTagsEditViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        val binding = TagsFollowedEditListItemBinding.inflate(inflater, parent, false)
        return FollowedTagsEditViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowedTagsEditViewHolder, position: Int) {
        var userFollowedTag: UserTags = userTagList.get(position)
        holder.bind(userFollowedTag)
        holder.binding.tagFollowedBtn.setOnClickListener {
            val res = viewModel.isFollowing(userTagList[position].tagDescription, position)
            if (res.equals(true)) {
                viewModel.unfollowTag(userTagList[position].tagDescription, position)
                holder.binding.tagFollowedBtn.text = "UNFOLLOW"
            } else {
                viewModel.followTag(userTagList[position].tagDescription, position)
                holder.binding.tagFollowedBtn.text = "FOLLOWING"
            }
        }
    }
}

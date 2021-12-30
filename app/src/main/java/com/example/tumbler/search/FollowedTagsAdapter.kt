package com.example.tumbler.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tumbler.databinding.TagsFollowedRvListItemsBinding
import com.example.tumbler.model.entity.search.UserTags

class FollowedTagsAdapter(val viewModel: SearchViewModel) : RecyclerView.Adapter<FollowedTagsAdapter.FollowedTagsViewHolder>() {

    var userTagList = listOf<UserTags>()

    override fun getItemCount() = userTagList.count()

    fun setList(userTags: List<UserTags>) {
        this.userTagList = userTags
        notifyDataSetChanged()
    }

    class FollowedTagsViewHolder(val binding: TagsFollowedRvListItemsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(userTags: UserTags) {
            binding.userTagName.text = userTags.tagDescription
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowedTagsViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        val binding = TagsFollowedRvListItemsBinding.inflate(inflater, parent, false)
        return FollowedTagsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowedTagsViewHolder, position: Int) {
        var userFollowedTag: UserTags = userTagList.get(position)
        holder.bind(userFollowedTag)
    }
}

package com.example.tumbler.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tumbler.databinding.RecommendedTagsListItemBinding
import com.example.tumbler.model.entity.search.Tags
/**
 * The adapter that manages the recycler view of the tags that Re suggested to the user
 * */
class RecommendedTagsAdapter(val viewModel: SearchViewModel) : RecyclerView.Adapter<RecommendedTagsAdapter.RecommendedTagsViewHolder>() {

    var tagsList = listOf<Tags>()

    override fun getItemCount(): Int = tagsList.count()

    fun setList(allTags: List<Tags>) {
        this.tagsList = allTags
        notifyDataSetChanged()
    }

    class RecommendedTagsViewHolder(val binding: RecommendedTagsListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tag: Tags) {
            binding.tagName.text = "# ${tag.tagDescription}"

            if (tag.followed) {
                binding.btnFollowTag.text = "UNFOLLOW"
            } else {
                binding.btnFollowTag.text = "FOLLOW"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendedTagsViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        val binding = RecommendedTagsListItemBinding.inflate(inflater, parent, false)
        return RecommendedTagsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecommendedTagsViewHolder, position: Int) {
        var recommendedTag: Tags = tagsList.get(position)
        holder.bind(recommendedTag)
        holder.binding.btnFollowTag.setOnClickListener {
            if (recommendedTag.followed) {
                viewModel.unfollowTag(tagsList[position].tagDescription, position)
                holder.binding.btnFollowTag.text = "FOLLOW"
                recommendedTag.followed = false
            } else {
                viewModel.followTag(tagsList[position].tagDescription, position)
                holder.binding.btnFollowTag.text = "UNFOLLOW"
                recommendedTag.followed = true
            }

            notifyDataSetChanged()
        }
    }
}

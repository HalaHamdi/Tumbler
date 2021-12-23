package com.example.tumbler.search

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tumbler.databinding.RecommendedTagsListItemBinding
import com.example.tumbler.model.entity.search.Tags
import java.io.InputStream
import java.net.URL


class RecommendedTagsAdapter(val viewModel: SearchViewModel):RecyclerView.Adapter<RecommendedTagsAdapter.RecommendedTagsViewHolder>() {

    var tagsList = listOf<Tags>()

    override fun getItemCount(): Int =tagsList.count()

    fun setList( allTags:List<Tags>){
        this.tagsList=allTags
        notifyDataSetChanged()

    }

    class RecommendedTagsViewHolder(val binding: RecommendedTagsListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tag: Tags){
            binding.tagName.text=tag.tagDescription

//            val `in`: InputStream = URL(tag.tagImage).openStream()
//            val bmp = BitmapFactory.decodeStream(`in`)
//           check that this is the way we change the image
//            binding.tagImage.setImageBitmap(bmp)
            binding.tagImage.setImageResource(tag.tagImage.toInt())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendedTagsViewHolder {
        var inflater=LayoutInflater.from(parent.context)
        val binding=RecommendedTagsListItemBinding.inflate(inflater, parent, false)
        return RecommendedTagsViewHolder(binding)
    }


    override fun onBindViewHolder(holder: RecommendedTagsViewHolder, position: Int) {
        var recommendedTag:Tags=tagsList.get(position)
        holder.bind(recommendedTag)
    }

}
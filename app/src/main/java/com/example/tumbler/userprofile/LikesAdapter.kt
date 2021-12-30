package com.example.tumbler.userprofile

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.tumbler.R
import com.example.tumbler.databinding.PostItemBinding
import com.example.tumbler.model.entity.userprofile.LikedPosts
import com.example.tumbler.model.entity.userprofile.Post
import com.example.tumbler.model.entity.userprofile.PostsLiked

class LikesAdapter(val viewModel: LikesViewModel) : RecyclerView.Adapter<LikesAdapter.LikesViewHolder>() {
    var likedposts = listOf<PostsLiked>()
    override fun getItemCount(): Int = likedposts.size

    fun setList(allPosts: List<PostsLiked>) {
        this.likedposts = allPosts
        notifyDataSetChanged()
    }

    class LikesViewHolder(val binding: PostItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post: PostsLiked) {
            binding.postLoveIcon.setImageResource(R.drawable.ic_baseline_love_red_button)
            binding.userNamePost.text =post.blogUsername
            DownloadImageFromInternet(binding.profilePhotoPost).execute(post.blogAvatar)
            binding.postContent.loadData("<style>img{display: inline;height: auto;max-width: 100%;}</style>" + post.postBody
                , "text/html", "UTF-8")
        }
    }
    @SuppressLint("StaticFieldLeak")
    @Suppress("DEPRECATION")
    class DownloadImageFromInternet(var imageView: ImageView) : AsyncTask<String, Void, Bitmap?>() {

        override fun doInBackground(vararg urls: String): Bitmap? {
            val imageURL = urls[0]
            var image: Bitmap? = null
            try {
                val `in` = java.net.URL(imageURL).openStream()
                image = BitmapFactory.decodeStream(`in`)
            } catch (e: Exception) {
                Log.e("Error Message", e.message.toString())
                e.printStackTrace()
            }
            return image
        }
        override fun onPostExecute(result: Bitmap?) {
            imageView.setImageBitmap(result)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):LikesViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        val binding = PostItemBinding.inflate(inflater, parent, false)
        return LikesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LikesViewHolder, position: Int) {
        val posts: PostsLiked =likedposts.get(position)
        holder.bind(posts)
    }
}
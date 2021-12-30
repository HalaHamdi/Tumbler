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
import com.example.tumbler.databinding.PostItemBinding
import com.example.tumbler.model.entity.userprofile.Post

class BlogProfilePostsAdapter (val viewModel: UserBlogViewModel) : RecyclerView.Adapter<BlogProfilePostsAdapter.PostsViewHolder>() {
        var posts = listOf<Post>()
        override fun getItemCount(): Int = posts.size

        fun setList(allPosts: List<Post>) {
            this.posts = allPosts
            notifyDataSetChanged()
        }

        class PostsViewHolder(val binding: PostItemBinding) : RecyclerView.ViewHolder(binding.root) {
            fun bind(post: Post) {
                binding.userNamePost.text = post.blog_username
                DownloadImageFromInternet(binding.profilePhotoPost).execute(post.blog_avatar)
                binding.postContent.loadData("<style>img{display: inline;height: auto;max-width: 100%;}</style>" + post.post_body, "text/html", "UTF-8")
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
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):PostsViewHolder {
            var inflater = LayoutInflater.from(parent.context)
            val binding = PostItemBinding.inflate(inflater, parent, false)
            return PostsViewHolder(binding)
        }

        override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
            val posts: Post = posts.get(position)
            holder.bind(posts)
        }
}
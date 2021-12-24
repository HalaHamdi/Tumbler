package com.example.tumbler.search

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.tumbler.databinding.RecommendedBlogsListItemBinding
import com.example.tumbler.model.entity.search.Blogs
import com.example.tumbler.userprofile.FollowingAdapter
import java.io.InputStream
import java.net.URL


class RecommendedBlogsAdapter(val viewModel: SearchViewModel):RecyclerView.Adapter<RecommendedBlogsAdapter.RecommendedBlogsViewHolder>() {

    var blogsList = listOf<Blogs>()

    override fun getItemCount(): Int =blogsList.count()

    fun setList(allBlogs: List<Blogs>){
        this.blogsList=allBlogs
        notifyDataSetChanged()

    }

    class RecommendedBlogsViewHolder(val binding: RecommendedBlogsListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(blog: Blogs){
        binding.blogName.text=blog.username
            Log.i("Hala",blog.avatar.toString())
            FollowingAdapter.DownloadImageFromInternet(binding.blogImage).execute(blog.avatar)
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
            }
            catch (e: Exception) {
                Log.e("Error Message", e.message.toString())
                e.printStackTrace()
            }
            return image
        }
        override fun onPostExecute(result: Bitmap?) {
            imageView.setImageBitmap(result)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendedBlogsViewHolder{
        var inflater= LayoutInflater.from(parent.context)
        val binding=RecommendedBlogsListItemBinding.inflate(inflater, parent, false)
        return RecommendedBlogsViewHolder(binding)
    }


    override fun onBindViewHolder(holder: RecommendedBlogsViewHolder, position: Int) {
        var recommendedBlog:Blogs=blogsList.get(position)
        holder.bind(recommendedBlog)
        holder.binding.btnFollowBlog.setOnClickListener{
            if(viewModel.blogsFollowedLiveData.value?.get(position)==true){
                    viewModel.unfollowBlog(blogsList[position].id,position)

                holder.binding.btnFollowBlog.text = "Follow"
            }
            else{
                viewModel.followBlog(blogsList[position].id,position)
                holder.binding.btnFollowBlog.text= "UnFollow"
            }
            notifyDataSetChanged()

        }

    }

}
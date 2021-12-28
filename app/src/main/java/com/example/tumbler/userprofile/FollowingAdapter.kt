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
import com.example.tumbler.databinding.FollowingsListItemBinding
import com.example.tumbler.model.entity.userprofile.Following

class FollowingAdapter(val viewModel: FollowingViewModel) : RecyclerView.Adapter<FollowingAdapter.FollowingViewHolder>() {

    var followings = listOf<Following>()
    override fun getItemCount(): Int = followings.size

    fun setList(allFollowings: List<Following>) {
        this.followings = allFollowings
        notifyDataSetChanged()
    }

    class FollowingViewHolder(val binding: FollowingsListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(following: Following) {
            binding.followingName.text = following.blogUsername
            DownloadImageFromInternet(binding.followingImage).execute(following.blogAvatar)
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowingViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        val binding = FollowingsListItemBinding.inflate(inflater, parent, false)
        return FollowingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowingViewHolder, position: Int) {
        val following: Following = followings.get(position)
        holder.bind(following)
    }
}

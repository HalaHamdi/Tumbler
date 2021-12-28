package com.example.tumbler.activityandmessages

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tumbler.R
import com.example.tumbler.model.entity.chat.Chat_User_Data

class UserRecyclerView : RecyclerView.Adapter<UserRecyclerView.UserViewHolder>() {
    var userList: ArrayList<Chat_User_Data> = ArrayList()

    fun setList(userList: ArrayList<Chat_User_Data>) {
        this.userList = userList
        notifyDataSetChanged()
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var iv_userImage: ImageView = itemView.findViewById(R.id.iv_item_user_image)
        var tv_username: TextView = itemView.findViewById(R.id.tv_item_username)
        var tv_message: TextView = itemView.findViewById(R.id.tv_item_message)
        fun bind(user: Chat_User_Data) {
            iv_userImage.setImageResource(user.imgId)
            tv_username.text = user.name
            tv_message.text = user.message
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        var view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.chat_list_item, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        var user: Chat_User_Data = userList.get(position)
        holder.bind(user)
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}

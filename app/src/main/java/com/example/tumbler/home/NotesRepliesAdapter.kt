package com.example.tumbler.home

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.tumbler.BaseApplication
import com.example.tumbler.R
import com.example.tumbler.databinding.NotesItemBinding
import com.example.tumbler.databinding.PostItemBinding
import com.example.tumbler.model.entity.dashboard.DashboardPost
import com.example.tumbler.model.entity.dashboard.RepliesPage
import com.example.tumbler.userprofile.FollowingAdapter

class NotesRepliesAdapter(val viewModel: HomeViewModel) : RecyclerView.Adapter<NotesRepliesAdapter.NoteViewHolder>() {
    var notesList = ArrayList<RepliesPage>()

    fun setlist(ntsList: ArrayList<RepliesPage>) {
        this.notesList.addAll(ntsList)
        notifyDataSetChanged()
    }

    override fun getItemCount() = notesList.size

    class NoteViewHolder(val binding: NotesItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(note: RepliesPage) {
            binding.notesText.text = note.replyText
            binding.usernameTxt.text = note.blogUsername
            FollowingAdapter.DownloadImageFromInternet(binding.notesUserImage).execute(note.blogAvatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        val binding = NotesItemBinding.inflate(inflater, parent, false)
        return NoteViewHolder(binding)
//            .inflate(R.layout.post_item,parent,false))
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {

        var note: RepliesPage = notesList.get(position)

        holder.bind(note)
    }


}
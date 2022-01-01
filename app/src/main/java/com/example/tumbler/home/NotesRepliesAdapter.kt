package com.example.tumbler.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tumbler.databinding.NotesItemBinding
import com.example.tumbler.model.entity.dashboard.RepliesPage
import com.example.tumbler.userprofile.FollowingAdapter

/**
 * Adapter for recycler view that shows users notes replies of a post
 * @property[viewModel] View Model of home page passed to call its functions to deal with remote repository when user takes action
 */
class NotesRepliesAdapter(val viewModel: HomeViewModel) : RecyclerView.Adapter<NotesRepliesAdapter.NoteViewHolder>() {
    var notesList = ArrayList<RepliesPage>()

    /**
     * function to update recycler view list and notify the UI that list has changed
     * @param[ntsList] array of replies info to add at end of the list
     */
    fun setlist(ntsList: ArrayList<RepliesPage>) {
        this.notesList.addAll(ntsList)
        notifyDataSetChanged()
    }

    /**
     * function to get maximum number of items in the list
     */
    override fun getItemCount() = notesList.size

    /**
     * holder class for single item view-> it handles updating the UI
     * @property[binding] binding object of single item to deal with UI
     */
    class NoteViewHolder(val binding: NotesItemBinding) : RecyclerView.ViewHolder(binding.root) {

        /**
         * function that updates ui of single item (user info in this adapter) according to passed object
         * @param[note] note info that should be shown in UI
         */
        fun bind(note: RepliesPage) {
            binding.notesText.text = note.replyText
            binding.usernameTxt.text = note.blogUsername
            FollowingAdapter.DownloadImageFromInternet(binding.notesUserImage).execute(note.blogAvatar)
        }
    }

    /**
     * function to be called when making new view to make holder for it
     * it sets binding object and inflate the layer
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        val binding = NotesItemBinding.inflate(inflater, parent, false)
        return NoteViewHolder(binding)
//            .inflate(R.layout.post_item,parent,false))
    }

    /**
     * function to be called whenever an item is popping to screen-> it get the item and updates the UI
     */
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {

        var note: RepliesPage = notesList.get(position)

        holder.bind(note)
    }
}

package com.example.tumbler

import android.util.Log
import androidx.lifecycle.*
import com.example.tumbler.model.entity.addpost.CreatePost
import com.example.tumbler.model.entity.addpost.CreatePostBody
import com.example.tumbler.model.network.RemoteRepository
import kotlinx.coroutines.launch

class CreatePostViewModel(private val remoteRepository: RemoteRepository) : ViewModel() {

    private var createPostMutableLiveData = MutableLiveData<CreatePost>()
    val createPostLiveData: LiveData<CreatePost> get() = createPostMutableLiveData

    fun createPost(token: String, createPostBody: CreatePostBody, blogId: Int) = viewModelScope.launch {
        Log.i("Hala", "in model")

        var result = remoteRepository.createPost(token, createPostBody, blogId)
        Log.i("Hala", result.toString())
        if (result.isSuccessful) {
            Log.i("Hala", "sucessful Creation Of post")
        } else {
            Log.i("Hala", "error creating post=")
            Log.i("Hala", "error = ${result.message()}")
        }
    }
}

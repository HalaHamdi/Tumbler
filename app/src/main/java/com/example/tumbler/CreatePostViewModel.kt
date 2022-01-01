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

    private var imageMutableLiveData = MutableLiveData<String>()
    val imageLiveData: LiveData<String> get() = imageMutableLiveData

    fun createPost(token: String, createPostBody: CreatePostBody, blogId: Int) = viewModelScope.launch {

        var result = remoteRepository.createPost(token, createPostBody, blogId)
        Log.i("Hala", result.toString())
        if (result.isSuccessful) {
            Log.i("Hala", "sucessful Creation Of post")
        } else {
            Log.i("Hala", "error  creating post= ${result.message()}")
        }
    }

    fun uploadImage(token: String, img: String) = viewModelScope.launch {
        var imgresult = remoteRepository.uploadPhoto(token, img)
        imageMutableLiveData.postValue(imgresult)
        Log.i("Hala", "mUTALBLE = ${imageMutableLiveData.value}")
        Log.i("Hala", "live data  = ${imageLiveData.value}")
    }
}

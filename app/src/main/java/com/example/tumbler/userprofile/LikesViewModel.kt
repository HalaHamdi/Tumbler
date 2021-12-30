package com.example.tumbler.userprofile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tumbler.BaseApplication
import com.example.tumbler.model.entity.userprofile.PostsLiked
import com.example.tumbler.model.network.RemoteRepository
import kotlinx.coroutines.launch

class LikesViewModel(private val remoteRepository: RemoteRepository) : ViewModel(){

    private var _LikesPostMutalbleLiveData = MutableLiveData<List<PostsLiked>>()
    val likesPostsLiveData: LiveData<List<PostsLiked>> get() = _LikesPostMutalbleLiveData

    fun getlikedPosts() = viewModelScope.launch {
        val liked: List<PostsLiked> = remoteRepository.getPostsLiked(BaseApplication.user.access_token,BaseApplication.user.blog_id)
        _LikesPostMutalbleLiveData.postValue(liked)
    }
}
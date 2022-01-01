package com.example.tumbler.userprofile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tumbler.BaseApplication
import com.example.tumbler.model.entity.Meta
import com.example.tumbler.model.entity.userprofile.Post
import com.example.tumbler.model.network.RemoteRepository
import kotlinx.coroutines.launch

class UserBlogViewModel (private val remoteRepository: RemoteRepository) : ViewModel() {
    private var userBlogMutableLiveData = MutableLiveData<List<Post>>()
    val userBlogLiveData: LiveData<List<Post>> get() = userBlogMutableLiveData

    /**
     * Function to get all submitted posts by the user to be shown in profile
     */
    fun getsubmittedPosts(blogID:Int) = viewModelScope.launch {
        val posts: List<Post> = remoteRepository.getPostSubmitted(
            blogID,
            BaseApplication.user.access_token)
        userBlogMutableLiveData.postValue(posts)
    }
}
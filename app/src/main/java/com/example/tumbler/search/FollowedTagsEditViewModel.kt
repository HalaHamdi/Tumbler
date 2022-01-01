package com.example.tumbler.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tumbler.BaseApplication
import com.example.tumbler.model.entity.search.UserTags
import com.example.tumbler.model.network.RemoteRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


/**
 * The view model that holds the live data of the tags that the user followed
 * */
class FollowedTagsEditViewModel(private val remoteRepository: RemoteRepository) : ViewModel() {
    private var _userTagsEditMutableLiveData = MutableLiveData<List<UserTags>>()
    val userTagsEditLiveData: LiveData<List<UserTags>> get() = _userTagsEditMutableLiveData

    private var isFollowingTagMutableLiveData = MutableLiveData<Boolean>()
    val isFollowingTagLiveData: LiveData<Boolean>get() = isFollowingTagMutableLiveData

    fun getUserTagsEdit() = viewModelScope.launch {
        val userTags: List<UserTags> = remoteRepository.getTagsFollowed(BaseApplication.user.access_token)
        _userTagsEditMutableLiveData.postValue(userTags)
    }
    fun followTag(tag_description: String, position: Int) = viewModelScope.launch {
        remoteRepository.followTag(BaseApplication.user.access_token, tag_description)
    }

    fun unfollowTag(tag_description: String, position: Int) = viewModelScope.launch {
        remoteRepository.unfollowTag(BaseApplication.user.access_token, tag_description)
    }


  fun isFollowing(tag_description: String, position: Int) {
viewModelScope.launch {
    var isfollowing:Boolean =
        remoteRepository.isFollowingTag(BaseApplication.user.access_token, tag_description)
    Log.i("Hala", "view model= ${isfollowing.toString()}")
    isFollowingTagMutableLiveData.postValue(isfollowing)
}
    }
}

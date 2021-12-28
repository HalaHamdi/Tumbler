package com.example.tumbler.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tumbler.BaseApplication
import com.example.tumbler.model.entity.search.Blogs
import com.example.tumbler.model.entity.search.Tags
import com.example.tumbler.model.network.RemoteRepository
import kotlinx.coroutines.launch

class SearchViewModel(private val remoteRepository: RemoteRepository) : ViewModel() {

    private var _blogsMutableLiveData = MutableLiveData<List<Blogs>>()
    val blogsLiveData: LiveData<List<Blogs>> get() = _blogsMutableLiveData

    private var _tagsMutableLiveData = MutableLiveData<List<Tags>>()
    val tagsLiveData: LiveData<List<Tags>> get() = _tagsMutableLiveData

    fun getRecommendedBlogs() = viewModelScope.launch {
        val blogs: List<Blogs> = remoteRepository.recommendedBlogs(BaseApplication.user.access_token)
        _blogsMutableLiveData.postValue(blogs)
    }

    fun followBlog(blog_id: Int, position: Int) = viewModelScope.launch {
        remoteRepository.followBlog(BaseApplication.user.access_token, blog_id)
    }
    fun unfollowBlog(blog_id: Int, position: Int) = viewModelScope.launch {
        remoteRepository.unfollowBlog(BaseApplication.user.access_token, blog_id)
    }

    fun getRecommendedTags() = viewModelScope.launch {
        var tags: List<Tags> = remoteRepository.recommendedTags(BaseApplication.user.access_token)
        _tagsMutableLiveData.postValue(tags)
    }

    fun followTag(tag_description: String, position: Int) = viewModelScope.launch {
        remoteRepository.followTag(BaseApplication.user.access_token, tag_description)
    }

    fun unfollowTag(tag_description: String, position: Int) = viewModelScope.launch {
        remoteRepository.unfollowTag(BaseApplication.user.access_token, tag_description)
    }
}

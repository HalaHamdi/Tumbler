package com.example.tumbler.home

import androidx.lifecycle.*
import com.example.tumbler.model.entity.Post
import com.example.tumbler.model.entity.randomposts.Posts
// import com.example.tumbler.di.Base_URL
import com.example.tumbler.model.network.RemoteRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

class HomeViewModel(private val remoteRepository: RemoteRepository) : ViewModel() {

    private var _postsMutableLiveData = MutableLiveData<List<Post>>()
    val postsLiveData: LiveData<List<Post>> get() = _postsMutableLiveData

    fun getRandomPosts() = viewModelScope.launch {
        val randomPosts: List<Posts> = remoteRepository.getRandomPosts()
        lateinit var posts: MutableList<Post>
        if (randomPosts.isNullOrEmpty()) {} else {
            posts = MutableList<Post>(randomPosts.size) {
                Post(body = randomPosts[it].post_body, user = randomPosts[it].blog_username)
            }
        }
        _postsMutableLiveData.postValue(posts)
    }
}

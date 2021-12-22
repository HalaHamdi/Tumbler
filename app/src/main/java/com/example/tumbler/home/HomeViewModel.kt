package com.example.tumbler.home

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.*
import com.example.tumbler.BaseApplication
import com.example.tumbler.model.entity.Post
import com.example.tumbler.model.entity.dashboard.DashboardPost
import com.example.tumbler.model.entity.randomposts.Posts
// import com.example.tumbler.di.Base_URL
import com.example.tumbler.model.network.RemoteRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import org.koin.android.ext.android.inject

class HomeViewModel(private val remoteRepository: RemoteRepository) : ViewModel() {

    private var _postsMutableLiveData = MutableLiveData<List<Post>>()
    val postsLiveData: LiveData<List<Post>> get() = _postsMutableLiveData

    private var _dashhboardPostsMutableLiveData = MutableLiveData<List<DashboardPost>>()
    val dashhboardPostsMutableLiveData: LiveData<List<DashboardPost>> get() = _dashhboardPostsMutableLiveData




    fun getRandomPosts() = viewModelScope.launch {
        val randomPosts: List<Posts> = remoteRepository.getRandomPosts()
        Log.i("Nebo","got the get")
        var posts: MutableList<Post> = MutableList<Post>(randomPosts.size) {
            Post(body = randomPosts[it].post_body, user = randomPosts[it].blog_username)
        }
        _postsMutableLiveData.postValue(posts)
    }

    fun getDashboard() = viewModelScope.launch {
        _dashhboardPostsMutableLiveData.postValue(remoteRepository.Dashboard(BaseApplication.user.access_token))
    }

}

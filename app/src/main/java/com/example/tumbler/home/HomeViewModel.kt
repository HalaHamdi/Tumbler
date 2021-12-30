package com.example.tumbler.home

import android.util.Log
import androidx.lifecycle.*
import com.example.tumbler.BaseApplication
import com.example.tumbler.model.entity.Post
import com.example.tumbler.model.entity.dashboard.*
import com.example.tumbler.model.entity.randomposts.Posts
// import com.example.tumbler.di.Base_URL
import com.example.tumbler.model.network.RemoteRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

class HomeViewModel(private val remoteRepository: RemoteRepository) : ViewModel() {

    private var _postsMutableLiveData = MutableLiveData<List<Post>>()
    val postsLiveData: LiveData<List<Post>> get() = _postsMutableLiveData

    private var _dashhboardPostsMutableLiveData = MutableLiveData<List<DashboardPost>>()
    val dashhboardPostsMutableLiveData: LiveData<List<DashboardPost>> get() = _dashhboardPostsMutableLiveData

    private var _postRepliesMutableLiveData = MutableLiveData<List<RepliesPage>>()
    val postRepliesLiveData: LiveData<List<RepliesPage>> get() = _postRepliesMutableLiveData

    var curPage = 0

    private var maxPage: Int = 0
    var currentPost = 0
    private var curPostLikesNumMutableLiveData = MutableLiveData<Int>()
    val curPostLikesNum: LiveData<Int> get() = curPostLikesNumMutableLiveData

    private var curPostReblogsNumMutableLiveData = MutableLiveData<Int>()
    val curPostReblogsNum: LiveData<Int> get() = curPostReblogsNumMutableLiveData

    private var curPostReblogsMutableLiveData = MutableLiveData<List<ReblogsPage>>()
    val curPostReblogs: LiveData<List<ReblogsPage>> get() = curPostReblogsMutableLiveData

    private var curPostLikesMutableLiveData = MutableLiveData<List<LikesPage>>()
    val curPostLikes: LiveData<List<LikesPage>> get() = curPostLikesMutableLiveData

    fun getLikesList() = viewModelScope.launch {
        val likes: List<LikesPage> = remoteRepository.getLikes(currentPost, BaseApplication.user.access_token, 1)
        curPostLikesMutableLiveData.postValue(likes)
    }

    fun getReblogsList() = viewModelScope.launch {
        val reblogs: List<ReblogsPage> = remoteRepository.getReblogs(currentPost, BaseApplication.user.access_token, 1)
        curPostReblogsMutableLiveData.postValue(reblogs)
    }

    fun getPostNotes() {
        viewModelScope.launch {
            val notes: NotesResponse = remoteRepository.getNotes(currentPost, BaseApplication.user.access_token, 1)
            _postRepliesMutableLiveData.postValue(notes.replies.replies)
            curPostLikesNumMutableLiveData.postValue(notes.likes.pagination.total!!)
            curPostReblogsNumMutableLiveData.postValue(notes.reblogs.pagination.total!!)
        }
    }

    fun addComment(comment: String) {
        viewModelScope.launch {
            remoteRepository.reply(ReplyBody(comment), "Bearer ${BaseApplication.user.access_token}", currentPost)
        }
    }

    fun getRandomPosts() = viewModelScope.launch {
        val randomPosts: List<Posts> = remoteRepository.getRandomPosts()
        Log.i("Nebo", "got the get")
        var posts: MutableList<Post> = MutableList<Post>(randomPosts.size) {
            Post(body = randomPosts[it].post_body, user = randomPosts[it].blog_username)
        }
        _postsMutableLiveData.postValue(posts)
    }

    fun getDashboard() = viewModelScope.launch {
        Log.i("Dashboard Bug", "get dashboard")
        val posts: List<DashboardPost> = remoteRepository.Dashboard(BaseApplication.user.blog_id, BaseApplication.user.access_token, 1)
        _dashhboardPostsMutableLiveData.postValue(posts)
        maxPage = remoteRepository.getDashboardMaxPage(BaseApplication.user.blog_id, BaseApplication.user.access_token)
        curPage = 1
        Log.i("getDashboard", maxPage.toString())
    }

    fun updateDashboard() = viewModelScope.launch {
        Log.i("Dashboard Bug", "update dashboard")
        if (curPage < maxPage) {
            val posts: List<DashboardPost> = remoteRepository.Dashboard(
                BaseApplication.user.blog_id,
                BaseApplication.user.access_token,
                curPage + 1
            )
            _dashhboardPostsMutableLiveData.postValue(posts)
            curPage = curPage + 1
            Log.i("update dashboard", curPage.toString())
        }
    }

    fun LikePost(postID: Int, blogID: Int) {
        viewModelScope.launch {
            remoteRepository.LikePost(postID, blogID, BaseApplication.user.access_token)
        }
    }

    fun UnLikePost(postID: Int, blogID: Int) {
        viewModelScope.launch {
            remoteRepository.UnLike(postID, blogID, BaseApplication.user.access_token)
        }
    }
}

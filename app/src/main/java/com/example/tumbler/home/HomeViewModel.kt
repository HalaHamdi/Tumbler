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

/**
 * home page View Model: a class that communicate with remote repository to fetch data and
 * update dashboard Live data
 */
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

    /**
     * function to get users that like current post and push them into live data
     */
    fun getLikesList() = viewModelScope.launch {
        val likes: List<LikesPage> = remoteRepository.getLikes(currentPost, BaseApplication.user.access_token, 1)
        curPostLikesMutableLiveData.postValue(likes)
    }

    /**
     * fucntion to get users that rebloged current post and push them into live data
     */
    fun getReblogsList() = viewModelScope.launch {
        val reblogs: List<ReblogsPage> = remoteRepository.getReblogs(currentPost, BaseApplication.user.access_token, 1)
        curPostReblogsMutableLiveData.postValue(reblogs)
    }

    /**
     * function to get all notes of current post and push them all in live data
     */
    fun getPostNotes() {
        viewModelScope.launch {
            val notes: NotesResponse = remoteRepository.getNotes(currentPost, BaseApplication.user.access_token, 1)
            _postRepliesMutableLiveData.postValue(notes.replies.replies)
            curPostLikesNumMutableLiveData.postValue(notes.likes.pagination.total!!)
            curPostReblogsNumMutableLiveData.postValue(notes.reblogs.pagination.total!!)
        }
    }

    /**
     * function to update database that user commented on current post
     * @param[comment] comment message that user type
     */
    fun addComment(comment: String) {
        viewModelScope.launch {
            remoteRepository.reply(ReplyBody(comment), "Bearer ${BaseApplication.user.access_token}", currentPost)
        }
    }

    /**
     * function to fetch from remote repository random posts
     */
    fun getRandomPosts() = viewModelScope.launch {
        val randomPosts: List<Posts> = remoteRepository.getRandomPosts()
        Log.i("Nebo", "got the get")
        var posts: MutableList<Post> = MutableList<Post>(randomPosts.size) {
            Post(body = randomPosts[it].post_body, user = randomPosts[it].blog_username)
        }
        _postsMutableLiveData.postValue(posts)
    }

    /**
     * function to get from remote repository dashboard posts (first page only)  and push them into live data
     */
    fun getDashboard() = viewModelScope.launch {
        Log.i("Dashboard Bug", "get dashboard")
        val posts: List<DashboardPost> = remoteRepository.Dashboard(BaseApplication.user.blog_id, BaseApplication.user.access_token, 1)
        _dashhboardPostsMutableLiveData.postValue(posts)
        maxPage = remoteRepository.getDashboardMaxPage(BaseApplication.user.blog_id, BaseApplication.user.access_token)
        curPage = 1
        Log.i("getDashboard", maxPage.toString())
    }

    /**
     * function to be called when user nearly reach end of home to fetch more posts from remote repository
     */
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

    /**
     * function to notify remote repository that user liked a specific post
     * @param[postID] id of post user liked
     * @param[blogID] id of user blog who liked the post
     */
    fun LikePost(postID: Int, blogID: Int) {
        viewModelScope.launch {
            remoteRepository.LikePost(postID, blogID, BaseApplication.user.access_token)
        }
    }

    /**
     * function to notify remote repository that user unliked a specific post
     * @param[postID] id of post user unliked
     * @param[blogID] id of user blog who unliked the post
     */
    fun UnLikePost(postID: Int, blogID: Int) {
        viewModelScope.launch {
            remoteRepository.UnLike(postID, blogID, BaseApplication.user.access_token)
        }
    }
}

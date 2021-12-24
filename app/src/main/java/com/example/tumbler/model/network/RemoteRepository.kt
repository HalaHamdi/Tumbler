package com.example.tumbler.model.network

import android.content.Context
import android.util.Log
import android.widget.Toast
import android.widget.Toast.makeText
import com.example.tumbler.R
import com.example.tumbler.model.entity.LoginResponse.LoginRequest
import com.example.tumbler.model.entity.LoginResponse.Meta
import com.example.tumbler.model.entity.SignUpResponse.RequestBody
import com.example.tumbler.model.entity.addpost.CreatePostBody
import com.example.tumbler.model.entity.dashboard.Dashboard
import com.example.tumbler.model.entity.dashboard.DashboardPost
import com.example.tumbler.model.entity.randomposts.Posts
import com.example.tumbler.model.entity.search.Blogs
import com.example.tumbler.model.entity.search.SuggestedBlogs
import com.example.tumbler.model.entity.userprofile.Following
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class RemoteRepository(private val api: ServiceAPI) : RemoteRepositoryInterface {

    override suspend fun getRandomPosts(): List<Posts> {
        lateinit var randomPosts: List<Posts>
        withContext(Dispatchers.IO) {
            val result = api.getRandomPosts()
            if (result.isSuccessful) {
                if (result.body() != null) {
                    randomPosts = result.body()!!.response.posts
                } else {}
            } else {
                Log.i("err", result.message())
            }
        }
        return randomPosts
    }

    override suspend fun Dashboard(token :String): List<DashboardPost> {
        lateinit var dashboardPosts: List<DashboardPost>
        withContext(Dispatchers.IO) {
            val result = api.Dashboard("Bearer $token")
            Log.i("dashboard",result.toString())
            if (result.isSuccessful) {
                if (result.body() != null) {
                    dashboardPosts = result.body()!!.response.posts
                } else {}
            } else {
                Log.i("err", result.message())
            }
        }
        return dashboardPosts
    }

    override suspend fun createPost(token:String,createPostBody: CreatePostBody, blogId: Int)= withContext(Dispatchers.IO) {
        api.createPost(token,createPostBody,blogId)
    }

    override suspend fun SignUp(user: RequestBody) = withContext(Dispatchers.IO) {
        api.SignUp(user)
    }

    override suspend fun Login(user: LoginRequest) = withContext(Dispatchers.IO) {
        api.Login(user)
    }

    override suspend fun LikePost(postID: Int,blogID:Int, token: String) {
        withContext(Dispatchers.IO){
            val result = api.LikePost(postID,token)
            if (result.isSuccessful) {

            } else {
                Log.i("err", result.message())
            }
        }
    }

    override suspend fun isLiked(postID: Int, blogID: Int, token: String): Boolean? {
        var abbas:Boolean? = null
        withContext(Dispatchers.IO){
            val result = api.isLiked(postID,blogID, token)
            if (result.isSuccessful) {
                 abbas = result.body()!!.response.status
            } else {
                Log.i("err", result.message())
            }
        }
        //Log.i("TTT",abbas.toString())
        abbas = false
        return abbas
    }

    override suspend fun UnLike(postID: Int, blogID: Int, token: String) {
        withContext(Dispatchers.IO){
            val result = api.UnLike(postID,token)
            if (result.isSuccessful) {

            } else {
                Log.i("err", result.message())
            }
        }
    }

    override suspend fun recommendedBlogs(token: String): List<Blogs>  {
        Log.i("Hala","in the remote repo")
        lateinit var recommendedBlogs: List<Blogs>
        withContext(Dispatchers.IO) {
            val result = api.recommendedBlogs("Bearer $token")
            Log.i("Hala",result.toString())

            if (result.isSuccessful) {
                if (result.body() != null) {
                    recommendedBlogs = result.body()!!.response.blogs
                } else {
                    Log.i("Hala","empty blogs")
                }
            } else {
                Log.i("Hala", result.message())
            }
        }
        return recommendedBlogs
    }

    override suspend fun followBlog(token: String, blog_id: Int){
        withContext(Dispatchers.IO) {
            val result=api.followBlog("Bearer $token",blog_id)
            if (result.isSuccessful) {
                if (result.body() != null) {
                    Log.i("Hala","Successful Creation")
                } else {
                }
            } else {
                Log.i("Hala", result.message())
            }
        }
    }

    override suspend fun unfollowBlog(token: String, blog_id: Int){
        withContext(Dispatchers.IO) {
            val result=api.unfollowBlog("Bearer $token",blog_id)
            if (result.isSuccessful) {
                if (result.body() != null) {
                    Log.i("Hala","Successful unfollow of blog")
                } else {
                }
            } else {
                Log.i("Hala", result.message())
            }
        }
    }

    override suspend fun getFollowings(token: String): List<Following> {
        lateinit var following: List<Following>
        withContext(Dispatchers.IO) {
            val result = api.getFollowings("Bearer $token")
            Log.i("Hala",result.toString())

            if (result.isSuccessful) {
                if (result.body() != null) {
                    following = result.body()!!.response.followings
                } else {
                    Log.i("Hala","empty blogs")
                }
            } else {
                Log.i("Hala", result.message())
            }
        }
        return following
    }


}

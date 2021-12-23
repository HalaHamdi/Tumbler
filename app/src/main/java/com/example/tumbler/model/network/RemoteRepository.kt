package com.example.tumbler.model.network

import android.util.Log
import com.example.tumbler.model.entity.LoginResponse.LoginRequest
import com.example.tumbler.model.entity.LoginResponse.Meta
import com.example.tumbler.model.entity.SignUpResponse.RequestBody
import com.example.tumbler.model.entity.addpost.CreatePostBody
import com.example.tumbler.model.entity.dashboard.Dashboard
import com.example.tumbler.model.entity.dashboard.DashboardPost
import com.example.tumbler.model.entity.randomposts.Posts
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
        //abbas = false
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

}

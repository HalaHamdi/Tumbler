package com.example.tumbler.model.network

import android.util.Log
import com.example.tumbler.model.entity.LoginResponse.LoginRequest
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

    override suspend fun createPost(createPostBody: CreatePostBody, blogId: Int) = withContext(Dispatchers.IO) {
        Log.i("Hala", "iN REMOTE REPO")
        api.createPost(createPostBody, blogId)
    }

    override suspend fun SignUp(user: RequestBody) = withContext(Dispatchers.IO) {
        api.SignUp(user)
    }

    override suspend fun Login(user: LoginRequest) = withContext(Dispatchers.IO) {
        api.Login(user)
    }


}

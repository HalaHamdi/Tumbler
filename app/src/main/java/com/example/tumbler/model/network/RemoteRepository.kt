package com.example.tumbler.model.network

import android.util.Log
import com.example.tumbler.model.entity.LoginResponse.LoginRequest
import com.example.tumbler.model.entity.SignUpResponse.RequestBody
import com.example.tumbler.model.entity.addpost.CreatePostBody
import com.example.tumbler.model.entity.randomposts.Posts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteRepository(private val api: ServiceAPI) : RemoteRepositoryInterface {

    override suspend fun getRandomPosts(): List<Posts> {
        lateinit var randomPosts: List<Posts>
        withContext(Dispatchers.IO) {
            val result = api.getRandomPosts()
            Log.i("Nebo", result.toString())
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

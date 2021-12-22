package com.example.tumbler.model.network

import com.example.tumbler.model.entity.LoginResponse.LoginRequest
import com.example.tumbler.model.entity.LoginResponse.LoginResponse
import com.example.tumbler.model.entity.SignUpResponse.RequestBody
import com.example.tumbler.model.entity.SignUpResponse.SignupResponse
import com.example.tumbler.model.entity.addpost.CreatePostBody
import com.example.tumbler.model.entity.addpost.CreatePostResponse
import com.example.tumbler.model.entity.dashboard.Dashboard
import com.example.tumbler.model.entity.dashboard.DashboardPost
import com.example.tumbler.model.entity.randomposts.Posts
import retrofit2.Response

interface RemoteRepositoryInterface {
    suspend fun createPost(createPostBody: CreatePostBody, blogId: Int): Response<CreatePostResponse>
    suspend fun getRandomPosts(): List<Posts>

    suspend fun SignUp(user: RequestBody): Response<SignupResponse>
    suspend fun Login(user: LoginRequest): Response<LoginResponse>

    suspend fun Dashboard(token :String):List<DashboardPost>

}

package com.example.tumbler.model.network

import com.example.tumbler.model.entity.User
import retrofit2.Response
import retrofit2.http.GET

interface ServiceAPI {

    @GET("/typicode/demo/posts")
    suspend fun getAPIUsers(): Response<List<User>>
}
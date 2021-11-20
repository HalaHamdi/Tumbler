package com.example.tumbler.model.network

import com.example.tumbler.model.entity.User
import com.example.tumbler.model.entity.postbyid.PostByID
import com.example.tumbler.model.entity.postnotesbyid.PostNotesByID
import com.example.tumbler.model.entity.temp
import retrofit2.Response
import retrofit2.http.GET

interface ServiceAPI {

    @GET("/typicode/demo/posts")
    suspend fun getAPIUsers(): Response<List<User>>

    @GET("/Hmmammmm/dp/post")
    suspend fun getPostByID(): Response<PostByID>

    @GET("/Hmmammmm/dp/note")
    suspend fun getPostNotesByID(): Response<PostNotesByID>

    @GET("Hmmammmm/dp/abbas")
    suspend fun gettt(): Response<temp>
}

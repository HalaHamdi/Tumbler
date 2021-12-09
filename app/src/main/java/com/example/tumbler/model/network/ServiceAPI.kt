package com.example.tumbler.model.network

import com.example.tumbler.model.entity.User
import com.example.tumbler.model.entity.addpost.CreatePostBody
import com.example.tumbler.model.entity.addpost.CreatePostResponse
import com.example.tumbler.model.entity.postbyid.PostByID
import com.example.tumbler.model.entity.postnotesbyid.PostNotesByID
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ServiceAPI {

    @GET("/typicode/demo/posts")
    suspend fun getAPIUsers(): Response<List<User>>

    @GET("/Hmmammmm/dp/post")
    suspend fun getPostByID(): Response<PostByID>

    @GET("/Hmmammmm/dp/note")
    suspend fun getPostNotesByID(): Response<PostNotesByID>

    @POST("Hmmammmm/dp/post/{blogId}")
    suspend fun createPost(@Body createPostBody: CreatePostBody, @Path("blogId")blogId:Int): Response<CreatePostResponse>
}

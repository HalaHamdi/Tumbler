package com.example.tumbler.model.network

import com.example.tumbler.model.entity.addpost.CreatePostBody
import com.example.tumbler.model.entity.addpost.CreatePostResponse
import com.example.tumbler.model.entity.randomposts.RandomPosts
import retrofit2.Response
import retrofit2.http.*

interface ServiceAPI {

    @GET("/api/posts/random_posts")
    suspend fun getRandomPosts(): Response<RandomPosts>


    @POST("/abbas/{blogId}")
    suspend fun createPost(@Body createPostBody: CreatePostBody, @Path("blogId")blogId:Int): Response<CreatePostResponse>
}

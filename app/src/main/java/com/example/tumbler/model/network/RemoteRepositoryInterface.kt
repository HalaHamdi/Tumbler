package com.example.tumbler.model.network

import com.example.tumbler.model.entity.addpost.CreatePostBody
import com.example.tumbler.model.entity.addpost.CreatePostResponse
import com.example.tumbler.model.entity.randomposts.Posts
import com.example.tumbler.model.entity.randomposts.RandomPosts
import retrofit2.Response

interface RemoteRepositoryInterface {
    suspend fun createPost(createPostBody: CreatePostBody, blogId: Int): Response<CreatePostResponse>
    suspend fun getRandomPosts(): List<Posts>

}

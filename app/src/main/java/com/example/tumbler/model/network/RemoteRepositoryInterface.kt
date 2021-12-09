package com.example.tumbler.model.network

import com.example.tumbler.model.entity.User
import com.example.tumbler.model.entity.addpost.CreatePostBody
import com.example.tumbler.model.entity.addpost.CreatePostResponse
import com.example.tumbler.model.entity.postbyid.PostByID
import com.example.tumbler.model.entity.postnotesbyid.PostNotesByID
import retrofit2.Response

interface RemoteRepositoryInterface {
    suspend fun getAPIUsers(): Response<List<User>>
    suspend fun getPostByID(): Response<PostByID>
    suspend fun createPost(createPostBody: CreatePostBody, blogId: Int): Response<CreatePostResponse>
    suspend fun getPostNotesByID(): Response<PostNotesByID>
    //    suspend fun gettt():Response<temp>

}

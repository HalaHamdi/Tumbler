package com.example.tumbler.model.network

import com.example.tumbler.model.entity.User
import com.example.tumbler.model.entity.postbyid.PostByID
import com.example.tumbler.model.entity.postnotesbyid.PostNotesByID
import com.example.tumbler.model.entity.temp
import retrofit2.Response

interface RemoteRepositoryInterface {
    suspend fun getAPIUsers(): Response<List<User>>
    suspend fun getPostByID(): Response<PostByID>
    suspend fun getPostNotesByID(): Response<PostNotesByID>
    suspend fun gettt(): Response<temp>
}

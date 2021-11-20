package com.example.tumbler.model.network

import com.example.tumbler.model.entity.User
import retrofit2.Response

interface RemoteRepositoryInterface {
    suspend fun getAPIUsers(): Response<List<User>>
}

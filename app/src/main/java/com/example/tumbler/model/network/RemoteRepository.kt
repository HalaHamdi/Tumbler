package com.example.tumbler.model.network

import com.example.tumbler.model.entity.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class RemoteRepository(private val api: ServiceAPI) : RemoteRepositoryInterface {
    override suspend fun getAPIUsers(): Response<List<User>> = withContext(Dispatchers.IO) {
        api.getAPIUsers()
    }
}

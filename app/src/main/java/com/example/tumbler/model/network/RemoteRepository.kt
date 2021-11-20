package com.example.tumbler.model.network

import android.util.Log
import com.example.tumbler.model.entity.postnotesbyid.PostNotesByID
import com.example.tumbler.model.entity.temp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class RemoteRepository(private val api:ServiceAPI):RemoteRepositoryInterface {
    override suspend fun getAPIUsers()= withContext(Dispatchers.IO){
        api.getAPIUsers()
    }

    override suspend fun getPostByID()= withContext(Dispatchers.IO){
        api.getPostByID()
    }

    override suspend fun getPostNotesByID()= withContext(Dispatchers.IO){
        api.getPostNotesByID()
    }

    override suspend fun gettt()= withContext(Dispatchers.IO){
        api.gettt()
    }
}
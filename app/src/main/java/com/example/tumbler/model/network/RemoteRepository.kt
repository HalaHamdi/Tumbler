package com.example.tumbler.model.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class RemoteRepository(private val api:ServiceAPI):RemoteRepositoryInterface {
    /***
     * model function that returns a list of all users
     */
    override suspend fun getAPIUsers()= withContext(Dispatchers.IO){
        api.getAPIUsers()
    }
    /***
     * model function that retrieves a specific post by its id. (to be further improved)
     */
    override suspend fun getPostByID()= withContext(Dispatchers.IO){
        api.getPostByID()
    }
    /***
     * model function that retrieves some notes regarding a specific post. i.e. post likes, notes, and reblogs.(to be further improved)
     */
    override suspend fun getPostNotesByID()= withContext(Dispatchers.IO){
        api.getPostNotesByID()
    }

//    override suspend fun gettt()= withContext(Dispatchers.IO){
//        api.gettt()
//    }
}

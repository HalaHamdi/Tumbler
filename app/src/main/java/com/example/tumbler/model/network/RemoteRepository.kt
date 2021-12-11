package com.example.tumbler.model.network

import android.util.Log
import com.example.tumbler.model.entity.LoginResponse.LoginRequest
import com.example.tumbler.model.entity.LoginResponse.LoginResponse
import com.example.tumbler.model.entity.SignUpResponse.RequestBody
import com.example.tumbler.model.entity.SignUpResponse.SignupResponse
import com.example.tumbler.model.entity.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.http.Body


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

    override suspend fun SignUp(user: RequestBody)= withContext(Dispatchers.IO){
        api.SignUp(user)
    }


    override suspend fun Login(user: LoginRequest)= withContext(Dispatchers.IO){
        api.Login(user)
    }

//    override suspend fun gettt()= withContext(Dispatchers.IO){
//        api.gettt()
//    }
}

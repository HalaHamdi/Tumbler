package com.example.tumbler.model.network

import com.example.tumbler.model.entity.SignUpResponse.RequestBody
import com.example.tumbler.model.entity.SignUpResponse.SignupResponse
import com.example.tumbler.model.entity.User
import com.example.tumbler.model.entity.postbyid.PostByID
import com.example.tumbler.model.entity.postnotesbyid.PostNotesByID
import com.example.tumbler.model.entity.temp
import retrofit2.Response
import retrofit2.http.Body

interface RemoteRepositoryInterface {
    suspend fun getAPIUsers()
    suspend fun getPostByID()
    suspend fun getPostNotesByID()
    suspend fun SignUp(user: RequestBody):Response<SignupResponse>



//    suspend fun gettt():Response<temp>
}

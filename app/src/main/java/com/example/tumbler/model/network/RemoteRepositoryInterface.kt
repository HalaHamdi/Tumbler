package com.example.tumbler.model.network

import com.example.tumbler.model.entity.LoginResponse.LoginRequest
import com.example.tumbler.model.entity.LoginResponse.LoginResponse
import com.example.tumbler.model.entity.SignUpResponse.RequestBody
import com.example.tumbler.model.entity.SignUpResponse.SignupResponse
import com.example.tumbler.model.entity.User
import com.example.tumbler.model.entity.postbyid.PostByID
import com.example.tumbler.model.entity.postnotesbyid.PostNotesByID
import com.example.tumbler.model.entity.temp
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body

interface RemoteRepositoryInterface {
    suspend fun getAPIUsers(): Response<List<User>>
    suspend fun getPostByID(): Response<PostByID>
    suspend fun getPostNotesByID(): Response<PostNotesByID>
    suspend fun SignUp(user: RequestBody):Response<SignupResponse>
    suspend fun Login(user: LoginRequest):Response<LoginResponse>



//    suspend fun gettt():Response<temp>
}

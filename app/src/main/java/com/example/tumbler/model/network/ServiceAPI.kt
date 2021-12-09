package com.example.tumbler.model.network

import com.example.tumbler.model.entity.SignUpResponse.RequestBody
import com.example.tumbler.model.entity.SignUpResponse.SignupResponse
import com.example.tumbler.model.entity.User
import com.example.tumbler.model.entity.postbyid.PostByID
import com.example.tumbler.model.entity.postnotesbyid.PostNotesByID
import com.example.tumbler.model.entity.temp
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ServiceAPI {

//    @GET("https://api.tumbler.social/api/")
//    suspend fun getAPIUsers(): Response<List<User>>
//
//    @GET("/Hmmammmm/dp/post")
//    suspend fun getPostByID(): Response<PostByID>
//
//    @GET("/Hmmammmm/dp/note")
//    suspend fun getPostNotesByID(): Response<PostNotesByID>

    @POST("api/register/")
    suspend fun SignUp(@Body user: RequestBody):Response<SignupResponse>


}

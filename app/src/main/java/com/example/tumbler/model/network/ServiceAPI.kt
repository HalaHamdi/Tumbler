package com.example.tumbler.model.network

import com.example.tumbler.BaseApplication
import com.example.tumbler.model.entity.LoginResponse.LoginRequest
import com.example.tumbler.model.entity.LoginResponse.LoginResponse
import com.example.tumbler.model.entity.SignUpResponse.RequestBody
import com.example.tumbler.model.entity.SignUpResponse.SignupResponse
import com.example.tumbler.model.entity.addpost.CreatePostBody
import com.example.tumbler.model.entity.addpost.CreatePostResponse
import com.example.tumbler.model.entity.dashboard.Dashboard
import com.example.tumbler.model.entity.randomposts.RandomPosts
import retrofit2.Response
import retrofit2.http.*

interface ServiceAPI {


    @GET("/api/posts/random_posts")
    suspend fun getRandomPosts(): Response<RandomPosts>

    @Headers(
        "Accept: application/json",
        "Authorization: eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIzIiwianRpIjoiMjRhZWE4MmRlYWQ1OTRlZTg3YzhmYTk3MjljMWQ4OTZjZmVkOTExODVjMTE0N2Y3ZGM1ZDllN2FiNTE4NmY0YmM1YmU5MGZhYmE1YzJhOWEiLCJpYXQiOjE2MzkwNzMwODcuNTI3MjQyLCJuYmYiOjE2MzkwNzMwODcuNTI3MjQ2LCJleHAiOjE2NzA2MDkwODcuNTI0OTg0LCJzdWIiOiIxNyIsInNjb3BlcyI6W119.DPJTT862FXZawWEM8ACf78shy391my8oZaOXb7Dmkuh3QHFjgjoFjT_EeXL6OnHDvBKqBKWOsU4EpTUcLim_DNtwjgAksmsYJz8lX56b2GL4dvOm8Wp9r944K-hwOjUKN2_0r1C1stgO3e9SOg8bFnlT4sFF5VNd7UUx4JMq1inQ4qXKLhIpTTQqCemUjH4rTpjjrx03LQAzwPqEoZXgkVnQBxDy-mmErEGwUHKqXUH_NCg1D1abZRlXt1zMM85wwZOHfu3Xq9NozRs4fRQ_RWbTNJMf8Xme8pyEP5mxheux1VRyxlyVO_6KuepYRyQWmAr0CoY0GJqxCxPXf1T0oM_mGALGdZTI9KYp76bUKnNGLAeJ4U2tXpYW8WVsqNEBP7oDEe9Xgsl9UZiTbPoTaiajLY_QXuzebZV68xf_M51LOZeSxYHXoIZttpvBHwrbvBENgoXiUBLMolcT2Kt6mS8exJHXPWMu1YRS1qWyYYmRvYcm4X32OlyQiILBwP-rUx3bylE6ma8tletuGxvjej26Vw8Vla1tfdHujGI-A9u4SPixXNpihn2GHAACtzwhqgXb1E5NT2xUXSbVRybSFXfD2JghfgZFeVxLRYW3bTxhoNyq_rGicddfei34gSFv8_kORDvrukOkersajg7yLIcosKZVJ2FU5llqRii3B7Y"
    )
    @POST("/api/post/{blogId}")
    suspend fun createPost(@Body createPostBody: CreatePostBody, @Path("blogId")blogId: Int): Response<CreatePostResponse>

    @Headers("Accept: application/json")
    @POST("register")
    suspend fun SignUp(@Body user: RequestBody): Response<SignupResponse>

    @Headers("Accept: application/json")
    @POST("login")
    suspend fun Login(@Body user: LoginRequest): Response<LoginResponse>

    @Headers(
        "Accept: application/json",
    )
    @GET("posts/dashboard")
    suspend fun Dashboard(@Header("Authorization") token : String):Response<Dashboard>
}

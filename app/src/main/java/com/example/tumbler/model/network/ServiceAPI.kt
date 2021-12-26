package com.example.tumbler.model.network
import com.example.tumbler.model.entity.LoginResponse.LoginRequest
import com.example.tumbler.model.entity.LoginResponse.LoginResponse
import com.example.tumbler.model.entity.LoginResponse.Meta
import com.example.tumbler.model.entity.ObjectOfMeta
import com.example.tumbler.model.entity.SignUpResponse.RequestBody
import com.example.tumbler.model.entity.SignUpResponse.SignupResponse
import com.example.tumbler.model.entity.addpost.CreatePost
import com.example.tumbler.model.entity.addpost.CreatePostBody
import com.example.tumbler.model.entity.createNewTumblr.CreateBlogRequest
import com.example.tumbler.model.entity.dashboard.Dashboard
import com.example.tumbler.model.entity.like.IsLiked
import com.example.tumbler.model.entity.randomposts.RandomPosts
import retrofit2.Response
import retrofit2.http.*

interface ServiceAPI {


    @GET("/api/posts/random_posts")
    suspend fun getRandomPosts(): Response<RandomPosts>

    @Headers("Accept: application/json",
        "Content-Type: application/json")
    @POST("post/{blog_id}")
    suspend fun createPost(@Header("Authorization")token:String, @Body createPostBody: CreatePostBody, @Path("blog_id")blogId: Int): Response<CreatePost>

    @Headers("Accept: application/json")
    @POST("register")
    suspend fun SignUp(@Body user: RequestBody): Response<SignupResponse>

    @Headers("Accept: application/json")
    @POST("login")
    suspend fun Login(@Body user: LoginRequest): Response<LoginResponse>


    @Headers("Accept: application/json")
    @GET("posts/dashboard")
    suspend fun Dashboard(@Header("Authorization") token : String):Response<Dashboard>

    @Headers("Accept: application/json")
    @POST("/post/like/{post_id}")
    suspend fun LikePost(@Path("post_id") postID:Int,@Header("Authorization") token:String): Response<Meta>

    @Headers("Accept: application/json", "Content-Type:application/json")
    @POST("blog")
    suspend fun CreateNewTumblr(@Header("Authorization") token:String,@Body blogInfo: CreateBlogRequest): Response<ObjectOfMeta>

    @Headers("Accept: application/json", "Content-Type:application/json")
    @POST("logout")
    suspend fun Logout(@Header("Authorization") token:String): Response<ObjectOfMeta>

    @Headers("Accept: application/json")
    @GET("/post/like/{blog_id}/{post_id}")
    suspend fun isLiked(@Path("post_id") postID:Int,@Path("blog_id")blogID:Int, @Header("Authorization") token:String) : Response<IsLiked>

    @Headers("Accept: application/json")
    @DELETE("/post/like/{post_id}")
    suspend fun UnLike(@Path("post_id") postID:Int,@Header("Authorization") token:String): Response<Meta>



}

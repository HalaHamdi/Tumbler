package com.example.tumbler.model.network

import com.example.tumbler.BaseApplication
import com.example.tumbler.model.entity.LoginResponse.LoginRequest
import com.example.tumbler.model.entity.LoginResponse.LoginResponse
import com.example.tumbler.model.entity.LoginResponse.Meta
import com.example.tumbler.model.entity.SignUpResponse.RequestBody
import com.example.tumbler.model.entity.SignUpResponse.SignupResponse
import com.example.tumbler.model.entity.addpost.CreatePost
import com.example.tumbler.model.entity.addpost.CreatePostBody
import com.example.tumbler.model.entity.addpost.CreatePostResponse
import com.example.tumbler.model.entity.dashboard.Dashboard
import com.example.tumbler.model.entity.dashboard.DashboardPost
import com.example.tumbler.model.entity.like.IsLiked
import com.example.tumbler.model.entity.randomposts.Posts
import com.example.tumbler.model.entity.search.Blogs
import com.example.tumbler.model.entity.search.SuggestedBlogs
import com.example.tumbler.model.entity.userprofile.Following
import retrofit2.Response
import retrofit2.http.Header
import retrofit2.http.Path

interface RemoteRepositoryInterface {
    suspend fun createPost(token:String,createPostBody: CreatePostBody, blogId: Int): Response<CreatePost>
    suspend fun getRandomPosts(): List<Posts>

    suspend fun SignUp(user: RequestBody): Response<SignupResponse>
    suspend fun Login(user: LoginRequest): Response<LoginResponse>

    suspend fun Dashboard(token :String):List<DashboardPost>


    suspend fun LikePost(postID: Int,blogID:Int, token: String)
    suspend fun isLiked(postID:Int,blogID:Int, token:String): Boolean?
    suspend fun UnLike(postID: Int, blogID: Int, token: String)

    suspend fun recommendedBlogs(token:String): List<Blogs>
    suspend fun followBlog(token:String, blog_id:Int)
    suspend fun unfollowBlog(token:String, blog_id:Int)
    suspend fun getFollowings(token:String):List<Following>

}

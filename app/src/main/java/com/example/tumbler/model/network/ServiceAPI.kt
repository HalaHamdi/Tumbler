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
import com.example.tumbler.model.entity.dashboard.Notes
import com.example.tumbler.model.entity.dashboard.ReplyBody
import com.example.tumbler.model.entity.like.IsLiked
import com.example.tumbler.model.entity.randomposts.RandomPosts
import com.example.tumbler.model.entity.search.IsFollowingTag
import com.example.tumbler.model.entity.search.SuggestedBlogs
import com.example.tumbler.model.entity.search.SuggestedTags
import com.example.tumbler.model.entity.search.TagsFollowed
import com.example.tumbler.model.entity.settings.change_password
import com.example.tumbler.model.entity.userprofile.Followings
import retrofit2.Response
import retrofit2.http.*

interface ServiceAPI {

    // Post Requests
    @Headers("Accept: application/json")
    @POST("register")
    suspend fun SignUp(@Body user: RequestBody): Response<SignupResponse>

    @Headers("Accept: application/json")
    @POST("login")
    suspend fun Login(@Body user: LoginRequest): Response<LoginResponse>

    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    @POST("post/{blog_id}")
    suspend fun createPost(@Header("Authorization")token: String, @Body createPostBody: CreatePostBody, @Path("blog_id")blogId: Int): Response<CreatePost>

    @Headers(
        "Accept: application/json",
        "Content-Type:application/json"
    )
    @POST("post/like/{post_id}")
    suspend fun LikePost(@Path("post_id") postID: Int, @Header("Authorization") token: String): Response<Meta>

    @Headers("Accept: application/json", "Content-Type:application/json")
    @POST("blog")
    suspend fun CreateNewTumblr(@Header("Authorization") token: String, @Body blogInfo: CreateBlogRequest): Response<ObjectOfMeta>

    @Headers("Accept: application/json", "Content-Type:application/json")
    @POST("logout")
    suspend fun Logout(@Header("Authorization") token: String): Response<ObjectOfMeta>

    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    @POST("follow_blog/{blog_id}")
    suspend fun followBlog(@Header("Authorization")token: String, @Path("blog_id")blog_id: Int): Response<Meta>

    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    @POST("follow_tag/{tag_description}")
    suspend fun followTag(@Header("Authorization")token: String, @Path("tag_description")tag_description: String): Response<Meta>


    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    @POST("upload_base64_photo")
    suspend fun uploadPhoto(@Header("Authorization")token: String, @Body base64img:String): String

    // ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // get requests

    @GET("/api/posts/random_posts")
    suspend fun getRandomPosts(): Response<RandomPosts>

    @Headers("Accept: application/json")
    @GET("posts/dashboard")
    suspend fun Dashboard(@Header("Authorization") token: String, @Query("page")page: Int): Response<Dashboard>

    @Headers(
        "Accept: application/json",
        "Content-Type:application/json"
    )
    @GET("post/like/{blog_id}/{post_id}")
    suspend fun isLiked(@Path("post_id") postID: Int, @Path("blog_id")blogID: Int, @Header("Authorization") token: String): Response<IsLiked>

    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    @GET("blogs/trending")
    suspend fun recommendedBlogs(@Header("Authorization")token: String): Response<SuggestedBlogs>

    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    @GET("tag/suggesting")
    suspend fun recommendedTags(@Header("Authorization")token: String): Response<SuggestedTags>

    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    @GET("followings")
    suspend fun getFollowings(@Header("Authorization")token: String): Response<Followings>

    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    @GET("follow_tag")
    suspend fun getTagsFollowed(@Header("Authorization")token: String): Response<TagsFollowed>

    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    @GET("tag/is_following/{tag_description}")
    suspend fun isFollowingTag(@Header("Authorization")token: String, @Path("tag_description")tag_description: String): Response<IsFollowingTag>

    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    @GET("post_notes/{post_id}")
    suspend fun getNotes(@Header("Authorization")token: String, @Path("post_id")post_id: Int, @Query("page")page: Int): Response<Notes>

    // ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // Delete Requests
    @Headers(
        "Accept: application/json",
        "Content-Type:application/json"
    )
    @DELETE("post/like/{post_id}")
    suspend fun UnLike(@Path("post_id") postID: Int, @Header("Authorization") token: String): Response<Meta>

    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    @DELETE("follow_blog/{blog_id}")
    suspend fun unfollowBlog(@Header("Authorization")token: String, @Path("blog_id")blog_id: Int): Response<Meta>

    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    @DELETE("follow_tag/{tag_description}")
    suspend fun unfollowTag(@Header("Authorization")token: String, @Path("tag_description")tag_description: String): Response<Meta>

    @Headers("Accept: application/json", "Content-Type: application/json")
    @PUT("change_password")
    suspend fun ChangePassword(@Header("Authorization")token: String,@Body passwordInfo:change_password) : Response<ObjectOfMeta>

    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    @POST("post/reply/{post_id}")
    suspend fun reply(@Body replyBody: ReplyBody, @Header("Authorization")token: String, @Path("post_id")post_id: Int): Response<Meta>
}

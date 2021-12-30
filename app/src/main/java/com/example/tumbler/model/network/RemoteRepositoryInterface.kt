package com.example.tumbler.model.network

import com.example.tumbler.model.entity.LoginResponse.LoginRequest
import com.example.tumbler.model.entity.LoginResponse.LoginResponse
import com.example.tumbler.model.entity.ObjectOfMeta
import com.example.tumbler.model.entity.SignUpResponse.RequestBody
import com.example.tumbler.model.entity.SignUpResponse.SignupResponse
import com.example.tumbler.model.entity.addpost.CreatePost
import com.example.tumbler.model.entity.addpost.CreatePostBody
import com.example.tumbler.model.entity.createNewTumblr.CreateBlogRequest
import com.example.tumbler.model.entity.dashboard.*
import com.example.tumbler.model.entity.randomposts.Posts
import com.example.tumbler.model.entity.search.*
import com.example.tumbler.model.entity.settings.change_password
import com.example.tumbler.model.entity.userprofile.Following
import com.example.tumbler.model.entity.userprofile.Post
import retrofit2.Response

interface RemoteRepositoryInterface {
    suspend fun createPost(token: String, createPostBody: CreatePostBody, blogId: Int): Response<CreatePost>
    suspend fun uploadPhoto(token: String,base64img:String): String

    suspend fun getRandomPosts(): List<Posts>

    suspend fun SignUp(user: RequestBody): Response<SignupResponse>
    suspend fun Login(user: LoginRequest): Response<LoginResponse>

    suspend fun Dashboard(blog_id: Int, token: String, page: Int): List<DashboardPost>
    suspend fun CreateNewTumblr(token: String, blogInfo: CreateBlogRequest): Response<ObjectOfMeta>
    suspend fun Logout(token: String): Response<ObjectOfMeta>

    suspend fun LikePost(postID: Int, blogID: Int, token: String)
    suspend fun isLiked(postID: Int, blogID: Int, token: String): Boolean?
    suspend fun UnLike(postID: Int, blogID: Int, token: String)
    suspend fun ChangePassword(token: String,passwordInfo: change_password) : Response<ObjectOfMeta>

    suspend fun recommendedBlogs(token: String): List<Blogs>
    suspend fun getPostSubmitted(blogID: Int,token: String) :List<Post>

    suspend fun followBlog(token: String, blog_id: Int)
    suspend fun unfollowBlog(token: String, blog_id: Int)
    suspend fun getFollowings(token: String): List<Following>
    suspend fun recommendedTags(token: String): List<Tags>
    suspend fun followTag(token: String, tag_description: String)
    suspend fun unfollowTag(token: String, tag_description: String)
    suspend fun isFollowingTag(token: String, tag_description: String): Boolean

    suspend fun getTagsFollowed(token: String): List<UserTags>

    suspend fun getNumNotes(postID: Int, token: String): Int?
    suspend fun getNotes(postID: Int, token: String, page: Int): NotesResponse
    suspend fun getLikes(postID: Int, token: String, page: Int): ArrayList<LikesPage>
    suspend fun getReplies(postID: Int, token: String, page: Int): ArrayList<RepliesPage>
    suspend fun getReblogs(postID: Int, token: String, page: Int): ArrayList<ReblogsPage>
    suspend fun getDashboardMaxPage(blog_id: Int, token: String): Int

    suspend fun reply(replyBody: ReplyBody, token: String, post_id: Int)
}

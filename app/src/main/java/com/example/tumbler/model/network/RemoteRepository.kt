package com.example.tumbler.model.network

import android.content.Context
import android.util.Log
import android.widget.Toast
import android.widget.Toast.makeText
import com.example.tumbler.R
import com.example.tumbler.model.entity.LoginResponse.LoginRequest
import com.example.tumbler.model.entity.LoginResponse.Meta
import com.example.tumbler.model.entity.SignUpResponse.RequestBody
import com.example.tumbler.model.entity.addpost.CreatePostBody
import com.example.tumbler.model.entity.createNewTumblr.CreateBlogRequest
import com.example.tumbler.model.entity.dashboard.*
import com.example.tumbler.model.entity.randomposts.Posts
import com.example.tumbler.model.entity.search.Blogs
import com.example.tumbler.model.entity.search.SuggestedBlogs
import com.example.tumbler.model.entity.userprofile.Following
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class RemoteRepository(private val api: ServiceAPI) : RemoteRepositoryInterface {

    override suspend fun getRandomPosts(): List<Posts> {
        lateinit var randomPosts: List<Posts>
        withContext(Dispatchers.IO) {
            val result = api.getRandomPosts()
            if (result.isSuccessful) {
                if (result.body() != null) {
                    randomPosts = result.body()!!.response.posts
                } else {}
            } else {
                Log.i("err", result.message())
            }
        }
        return randomPosts
    }

    override suspend fun Dashboard(blog_id: Int, token :String): List<DashboardPost> {
        lateinit var dashboardPosts: List<DashboardPost>
        withContext(Dispatchers.IO) {
            val result = api.Dashboard("Bearer $token")
            Log.i("dashboard",result.toString())
            if (result.isSuccessful) {
                if (result.body() != null) {
                    dashboardPosts = result.body()!!.response.posts
                    dashboardPosts.forEach {
                        it.isLiked = isLiked(it.post_id,blog_id,token)!!
                        it.numNotes = getNumNotes(it.post_id,token)!!
                    }
                } else {}
            } else {
                Log.i("err", result.message())
            }
        }
        return dashboardPosts
    }



    override suspend fun CreateNewTumblr(token: String, blogInfo: CreateBlogRequest)= withContext(Dispatchers.IO) {
        api.CreateNewTumblr("Bearer $token",blogInfo)
    }

    override suspend fun Logout(token: String)= withContext(Dispatchers.IO) {
        api.Logout("Bearer $token")
    }

    override suspend fun createPost(token:String,createPostBody: CreatePostBody, blogId: Int)= withContext(Dispatchers.IO) {
        api.createPost(token,createPostBody,blogId)
    }



    override suspend fun SignUp(user: RequestBody) = withContext(Dispatchers.IO) {
        api.SignUp(user)
    }

    override suspend fun Login(user: LoginRequest) = withContext(Dispatchers.IO) {
        api.Login(user)
    }

    override suspend fun LikePost(postID: Int,blogID:Int, token: String) {
        withContext(Dispatchers.IO){
            val result = api.LikePost(postID,"Bearer $token")
            Log.i("Like Post",result.toString())
            if (result.isSuccessful) {
                Log.i("Like Post",result.toString())
            } else {
                Log.i("err in likepost", result.message())
            }
        }
    }

    override suspend fun isLiked(postID: Int, blogID: Int, token: String): Boolean? {
        var abbas:Boolean? = null
        withContext(Dispatchers.IO){
            val result = api.isLiked(postID,blogID, "Bearer $token")
            Log.i("Abbas", result.toString())
            if (result.isSuccessful) {
                 abbas = result.body()!!.response.status
            } else {
                Log.i("err in islike", result.message())
            }
        }
        //Log.i("TTT",abbas.toString())
        //abbas = true
        return abbas
    }

    override suspend fun UnLike(postID: Int, blogID: Int, token: String) {
        withContext(Dispatchers.IO){
            val result = api.UnLike(postID,"Bearer $token")
            Log.i("Unlike Post",result.toString())
            if (result.isSuccessful) {
                Log.i("Unlike Post",result.toString())
            } else {
                Log.i("err in unlike", result.message())
            }
        }
    }

    override suspend fun recommendedBlogs(token: String): List<Blogs>  {
        Log.i("Hala","in the remote repo")
        lateinit var recommendedBlogs: List<Blogs>
        withContext(Dispatchers.IO) {
            val result = api.recommendedBlogs("Bearer $token")
            Log.i("Hala",result.toString())

            if (result.isSuccessful) {
                if (result.body() != null) {
                    recommendedBlogs = result.body()!!.response.blogs
                } else {
                    Log.i("Hala","empty blogs")
                }
            } else {
                Log.i("Hala", result.message())
            }
        }
        return recommendedBlogs
    }

    override suspend fun followBlog(token: String, blog_id: Int){
        withContext(Dispatchers.IO) {
            val result=api.followBlog("Bearer $token",blog_id)
            if (result.isSuccessful) {
                if (result.body() != null) {
                    Log.i("Hala","Successful Creation")
                } else {
                }
            } else {
                Log.i("Hala", result.message())
            }
        }
    }

    override suspend fun unfollowBlog(token: String, blog_id: Int){
        withContext(Dispatchers.IO) {
            val result=api.unfollowBlog("Bearer $token",blog_id)
            if (result.isSuccessful) {
                if (result.body() != null) {
                    Log.i("Hala","Successful unfollow of blog")
                } else {
                }
            } else {
                Log.i("Hala", result.message())
            }
        }
    }

    override suspend fun getFollowings(token: String): List<Following> {
        lateinit var following: List<Following>
        withContext(Dispatchers.IO) {
            val result = api.getFollowings("Bearer $token")
            Log.i("Hala",result.toString())

            if (result.isSuccessful) {
                if (result.body() != null) {
                    following = result.body()!!.response.followings
                } else {
                    Log.i("Hala","empty blogs")
                }
            } else {
                Log.i("Hala", result.message())
            }
        }
        return following
    }

    override suspend fun getNumNotes(postID: Int, token: String): Int? {
        val notes = getNotes(postID,token,1)
        return notes.likes.pagination.total!! + notes.reblogs.pagination.total!! + notes.replies.pagination.total!!
    }

    override suspend fun getNotes(postID: Int, token: String, page:Int): NotesResponse {
        lateinit var notes: NotesResponse
        withContext(Dispatchers.IO) {
            val result = api.getNotes("Bearer $token",postID,page)
            Log.i("Notes",result.toString())
            if (result.isSuccessful) {
                if (result.body() != null) {
                    notes = result.body()!!.response!!
                } else {}
            } else {
                Log.i("err", result.message())
            }
        }
        return notes
    }

    override suspend fun getLikes(postID: Int, token: String, page: Int): ArrayList<LikesPage> {
        val notes = getNotes(postID,token,page)
        return notes.likes.likes
    }

    override suspend fun getReplies(postID: Int, token: String, page: Int): ArrayList<RepliesPage> {
        val notes = getNotes(postID,token,page)
        return notes.replies.replies
    }

    override suspend fun getReblogs(postID: Int, token: String, page:Int ): ArrayList<ReblogsPage> {
        val notes = getNotes(postID,token,page)
        return notes.reblogs.reblogs
    }
}

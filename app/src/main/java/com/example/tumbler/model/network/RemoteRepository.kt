package com.example.tumbler.model.network

import android.util.Log
import com.example.tumbler.model.entity.LoginResponse.LoginRequest
import com.example.tumbler.model.entity.SignUpResponse.RequestBody
import com.example.tumbler.model.entity.addpost.CreatePostBody
import com.example.tumbler.model.entity.createNewTumblr.CreateBlogRequest
import com.example.tumbler.model.entity.dashboard.*
import com.example.tumbler.model.entity.randomposts.Posts
import com.example.tumbler.model.entity.search.*
import com.example.tumbler.model.entity.settings.change_password
import com.example.tumbler.model.entity.userprofile.Following
import com.example.tumbler.model.entity.userprofile.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.lang.Exception
import kotlin.properties.Delegates

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

    override suspend fun getDashboardMaxPage(blog_id: Int, token: String): Int {
        try {
            var maxPage: Int = 0
            withContext(Dispatchers.IO) {
                val result = api.Dashboard("Bearer $token", 1)
                if (result.isSuccessful) {
                    if (result.body() != null) {
                        maxPage = result.body()!!.response.pagination.total_pages
                    } else {}
                } else {
                    Log.i("err", result.message())
                }
            }
            return maxPage
        } catch (e: Exception) {
            delay(1000)
            return getDashboardMaxPage(blog_id, token)
        }
    }

    override suspend fun Dashboard(blog_id: Int, token: String, page: Int): List<DashboardPost> {
        try {
            lateinit var dashboardPosts: List<DashboardPost>
            withContext(Dispatchers.IO) {
                val result = api.Dashboard("Bearer $token", page)
                Log.i("dashboard", "$result  $page")
                if (result.isSuccessful) {
                    if (result.body() != null) {
                        dashboardPosts = result.body()!!.response.posts
                        Log.i("Dashboard Pagination", result.body()!!.response.pagination.total_pages.toString())
//                        dashboardPosts.forEach {
//                            it.isLiked = isLiked(it.post_id,blog_id,token)!!
//                            it.numNotes = getNumNotes(it.post_id,token)!!
//                        }
                    } else {}
                } else {
                    Log.i("err", result.message())
                }
            }
            return dashboardPosts
        } catch (e: Exception) {
            delay(1000)
            return Dashboard(blog_id, token, page)
        }
    }

    override suspend fun CreateNewTumblr(token: String, blogInfo: CreateBlogRequest) = withContext(Dispatchers.IO) {
        api.CreateNewTumblr("Bearer $token", blogInfo)
    }

    override suspend fun Logout(token: String) = withContext(Dispatchers.IO) {
        api.Logout("Bearer $token")
    }

    override suspend fun createPost(token: String, createPostBody: CreatePostBody, blogId: Int) = withContext(Dispatchers.IO) {
        api.createPost("Bearer $token", createPostBody, blogId)
    }

    override suspend fun uploadPhoto(token: String, base64img: String): String {

        val result:String=api.uploadPhoto("Bearer $token",base64img)

        Log.i("Hala","Result of image base64 ${result}")
        return  result

    }

    override suspend fun SignUp(user: RequestBody) = withContext(Dispatchers.IO) {
        api.SignUp(user)
    }

    override suspend fun ChangePassword(token: String,passwordInfo: change_password)= withContext(Dispatchers.IO) {
        api.ChangePassword("Bearer $token",passwordInfo)
    }

    override suspend fun Login(user: LoginRequest) = withContext(Dispatchers.IO) {
        api.Login(user)
    }

    override suspend fun LikePost(postID: Int, blogID: Int, token: String) {
        withContext(Dispatchers.IO) {
            val result = api.LikePost(postID, "Bearer $token")
            Log.i("Like Post", result.toString())
            if (result.isSuccessful) {
                Log.i("Like Post", result.toString())
            } else {
                Log.i("err in likepost", result.message())
            }
        }
    }

    override suspend fun isLiked(postID: Int, blogID: Int, token: String): Boolean? {
        var isliked: Boolean? = null
        withContext(Dispatchers.IO) {
            val result = api.isLiked(postID, blogID, "Bearer $token")
            Log.i("Abbas", result.toString())
            if (result.isSuccessful) {
                isliked = result.body()!!.response.status
            } else {
                Log.i("err in islike", result.message())
            }
        }
        // Log.i("TTT",abbas.toString())
        // abbas = true
        return isliked
    }

    override suspend fun UnLike(postID: Int, blogID: Int, token: String) {
        withContext(Dispatchers.IO) {
            val result = api.UnLike(postID, "Bearer $token")
            Log.i("Unlike Post", result.toString())
            if (result.isSuccessful) {
                Log.i("Unlike Post", result.toString())
            } else {
                Log.i("err in unlike", result.message())
            }
        }
    }

    override suspend fun recommendedBlogs(token: String): List<Blogs> {

        try{ lateinit var recommendedBlogs: List<Blogs>
            withContext(Dispatchers.IO) {
                val result = api.recommendedBlogs("Bearer $token")
                Log.i("Hala", result.toString())

                if (result.isSuccessful) {
                    if (result.body() != null) {
                        recommendedBlogs = result.body()!!.response.blogs
                    } else {
                        Log.i("Hala", "empty blogs")
                    }
                } else {
                    Log.i("Hala", result.message())
                }
            }
            return recommendedBlogs}
        catch (e: Exception){
            delay(1000)
            return recommendedBlogs(token)

        }
    }

    override suspend fun followBlog(token: String, blog_id: Int) {
        withContext(Dispatchers.IO) {
            val result = api.followBlog("Bearer $token", blog_id)
            if (result.isSuccessful) {
                if (result.body() != null) {
                    Log.i("Hala", "Successful Creation")
                } else {
                }
            } else {
                Log.i("Hala", result.message())
            }
        }
    }

    override suspend fun unfollowBlog(token: String, blog_id: Int) {
        withContext(Dispatchers.IO) {
            val result = api.unfollowBlog("Bearer $token", blog_id)
            if (result.isSuccessful) {
                if (result.body() != null) {
                    Log.i("Hala", "Successful unfollow of blog")
                } else {
                }
            } else {
                Log.i("Hala", result.message())
            }
        }
    }

    override suspend fun getFollowings(token: String): List<Following> {
        try{
            lateinit var following: List<Following>
            withContext(Dispatchers.IO) {
                val result = api.getFollowings("Bearer $token")
                Log.i("Hala", result.toString())

                if (result.isSuccessful) {
                    if (result.body() != null) {
                        following = result.body()!!.response.followings
                    } else {
                        Log.i("Hala", "empty blogs")
                    }
                } else {
                    Log.i("Hala", result.message())
                }
            }
            return following
        }
        catch (e:Exception)
        { delay(1000)
            return  getFollowings(token)
        }
    }

    override suspend fun recommendedTags(token: String): List<Tags> {
        try{
            lateinit var recommendedTags: List<Tags>
            withContext(Dispatchers.IO) {
                val result = api.recommendedTags("Bearer $token")

                if (result.isSuccessful) {
                    if (result.body() != null) {
                        recommendedTags = result.body()!!.response.tags
                    } else {
                        Log.i("Hala", "empty tags")
                    }
                } else {
                    Log.i("Hala", result.message())
                }
            }
            return recommendedTags

        }
        catch(e:Exception){
            delay(1000)
            return  recommendedTags(token)
        }

    }

    override suspend fun followTag(token: String, tag_description: String) {
        withContext(Dispatchers.IO) {
            val result = api.followTag("Bearer $token", tag_description)
            if (result.isSuccessful) {
                if (result.body() != null) {
                    Log.i("Hala", "Successful follow of tag")
                } else {
                }
            } else {
                Log.i("Hala", result.message())
            }
        }
    }

    override suspend fun unfollowTag(token: String, tag_description: String) {
        withContext(Dispatchers.IO) {
            val result = api.unfollowTag("Bearer $token", tag_description)
            if (result.isSuccessful) {
                if (result.body() != null) {
                    Log.i("Hala", "Successful unfollow of tag")
                } else {
                }
            } else {
                Log.i("Hala", result.message())
            }
        }
    }

    override suspend fun isFollowingTag(token: String, tag_description: String): Boolean {
        var isFollowing by Delegates.notNull<Boolean>()
        val withContext = withContext(Dispatchers.IO) {
            val result = api.isFollowingTag("Bearer $token", tag_description)
            if (result.isSuccessful) {
                if (result.body() != null) {
                    isFollowing = result.body()!!.response.isFollowing
                    Log.i("Hala"," is following = ${isFollowing.toString()}" )
                } else {
                    Log.i("Hala", "empty tags")
                }
            } else {
                Log.i("Hala","In error ${ result.message()}")
            }
        }
        return isFollowing
    }

    override suspend fun getTagsFollowed(token: String): List<UserTags> {
        try{
        lateinit var followedTags: List<UserTags>
        Log.i("Hala", "Get tags followed remote repo")
        withContext(Dispatchers.IO) {
            val result = api.getTagsFollowed("Bearer $token")

            if (result.isSuccessful) {
                if (result.body() != null) {
                    followedTags = result.body()!!.response.userTags
                } else {
                    Log.i("Hala", "empty tags")
                }
            } else {
                Log.i("Hala", result.message())
            }
        }
        return followedTags}
        catch(e:Exception){
            delay(1000)
            return getTagsFollowed(token)
        }
    }
    override suspend fun getPostSubmitted(blogID: Int,token: String): List<Post> {
        try {
            lateinit var profilePosts: List<Post>
            withContext(Dispatchers.IO) {
                val result = api.getPostSubmitted(blogID,"Bearer $token")
                Log.i("Lalala", result.toString())
                if (result.isSuccessful) {
                    Log.i("Lalala1", "No Posts")
                    if (result.body() != null) {
                        Log.i("Lalala2", result.body()!!.response.toString())
                        profilePosts = result.body()!!.response.posts
                    } else {
                        Log.i("Elonsol", "No Posts")
                    }
                } else {
                    Log.i("Elonsol", result.message())
                }
            }
            return profilePosts
        } catch (e: Exception) {
            delay(1000)
            return getPostSubmitted(blogID,token)
        }
    }

    override suspend fun getNumNotes(postID: Int, token: String): Int? {
        val notes = getNotes(postID, token, 1)
        return notes.likes.pagination.total!! + notes.reblogs.pagination.total!! + notes.replies.pagination.total!!
    }

    override suspend fun getNotes(postID: Int, token: String, page: Int): NotesResponse {
        try {
            lateinit var notes: NotesResponse
            withContext(Dispatchers.IO) {
                val result = api.getNotes("Bearer $token", postID, page)
                Log.i("Notes", result.toString())
                if (result.isSuccessful) {
                    if (result.body() != null) {
                        notes = result.body()!!.response!!
                    } else {}
                } else {
                    Log.i("err", result.message())
                }
            }
            return notes
        } catch (e: Exception) {
            delay(1000)
            return getNotes(postID, token, page)
        }
    }

    override suspend fun getLikes(postID: Int, token: String, page: Int): ArrayList<LikesPage> {
        val notes = getNotes(postID, token, page)
        return notes.likes.likes
    }

    override suspend fun getReplies(postID: Int, token: String, page: Int): ArrayList<RepliesPage> {
        val notes = getNotes(postID, token, page)
        return notes.replies.replies
    }

    override suspend fun getReblogs(postID: Int, token: String, page: Int): ArrayList<ReblogsPage> {
        val notes = getNotes(postID, token, page)
        return notes.reblogs.reblogs
    }

    override suspend fun reply(replyBody: ReplyBody, token: String, post_id: Int) {
        try {
            withContext(Dispatchers.IO) {
                val result = api.reply(replyBody, token, post_id)
                Log.i("Reply", result.toString())
                Log.i("Reply", token)
                if (result.isSuccessful) {
                    if (result.body() != null) {
                    } else {}
                } else {
                    Log.i("err", result.message())
                }
            }
        } catch (e: Exception) {
            delay(1000)
            reply(replyBody, token, post_id)
        }
    }
}

package com.example.tumbler

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tumbler.model.entity.LoginResponse.LoginResponse
import com.example.tumbler.model.entity.Meta
import com.example.tumbler.model.entity.SignUpResponse.SignupResponse
import com.example.tumbler.model.entity.addpost.CreatePost
import com.example.tumbler.model.entity.addpost.CreatePostBody
import com.example.tumbler.model.entity.createNewTumblr.CreateBlogRequest
import com.example.tumbler.model.network.RemoteRepository
import com.google.gson.Gson
import kotlinx.coroutines.launch
import org.json.JSONObject

class CreateNewTumblrViewModel (private val remoteRepository: RemoteRepository) : ViewModel() {
    private var createNewTumblrMutableLiveData = MutableLiveData<com.example.tumbler.model.entity.Meta>()
    val createNewTumblrLiveData: LiveData<com.example.tumbler.model.entity.Meta> get() = createNewTumblrMutableLiveData

    fun CreateNewTumblr(blogInfo: CreateBlogRequest) = viewModelScope.launch {
        var gson: Gson = Gson()
        var result = remoteRepository.CreateNewTumblr(BaseApplication.user.access_token,blogInfo)
        Log.i("Abbas", result.body().toString())
        Log.i("Abbas", result.errorBody().toString())
        if (result.isSuccessful) {
            Log.i("Elonsol1", result.body()!!.meta.toString())
            createNewTumblrMutableLiveData.postValue(result.body()!!.meta)
        } else {
            //createNewTumblrMutableLiveData.postValue(result as Meta)
            Log.i("Elonsol4","error ")
            //Log.i("Elonsol5",(result as Meta).toString() )
        }
    }
}
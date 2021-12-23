package com.example.tumbler.userprofile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tumbler.model.entity.LoginResponse.LoginRequest
import com.example.tumbler.model.entity.LoginResponse.LoginResponse
import com.example.tumbler.model.network.RemoteRepository
import com.google.gson.Gson
import kotlinx.coroutines.launch
import org.json.JSONObject

class CreateNewTumblrViewModel(private val remoteRepository: RemoteRepository) : ViewModel() {
//    private var LoginMutableLiveData = MutableLiveData<LoginResponse>()
//    val LoginLiveData: LiveData<LoginResponse> get() = LoginMutableLiveData
//
//    fun Login(user: LoginRequest) = viewModelScope.launch {
//        Log.i("req", user.toString())
//        var result = remoteRepository.Login(user)
//        var gson: Gson = Gson()
//
//        Log.i("res", result.body().toString())
//        if (result.isSuccessful) {
//            if (result.body() != null) {
//                LoginMutableLiveData.postValue(result.body())
//                Log.i("sus", result.body().toString())
//            }
//        } else {
//            val jsonObj = JSONObject(result.errorBody()!!.charStream().readText())
//            LoginMutableLiveData.postValue(gson.fromJson(jsonObj.toString(), LoginResponse::class.java))
//            Log.i("err", jsonObj.toString())
//        }
//    }
}


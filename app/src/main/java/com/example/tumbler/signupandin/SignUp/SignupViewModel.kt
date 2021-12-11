package com.example.tumbler.signupandin.SignUp

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tumbler.model.entity.SignUpResponse.RequestBody
import com.example.tumbler.model.entity.SignUpResponse.Response
import com.example.tumbler.model.entity.SignUpResponse.SignupResponse
import com.example.tumbler.model.entity.postbyid.PostByID
import com.example.tumbler.model.network.RemoteRepository
import com.example.tumbler.model.network.RetroBuilder
import com.example.tumbler.model.network.ServiceAPI
import com.google.gson.Gson
import kotlinx.coroutines.launch
import org.json.JSONObject

class SignupViewModel (app: Application) : AndroidViewModel(app) {
    private var remoteRepository: RemoteRepository

    init {
        var serviceInstance = RetroBuilder.getRetroBuilder().create(ServiceAPI::class.java)
        remoteRepository = RemoteRepository(serviceInstance)
    }

    private var SignUpMutableLiveData = MutableLiveData<SignupResponse>()
    val SignUpLiveData: LiveData<SignupResponse> get() = SignUpMutableLiveData

    private var SignUpSuccesfulMutableLiveData=MutableLiveData<Int>(0)
    val SignUpSuccesfulLiveData:LiveData<Int> get()=SignUpSuccesfulMutableLiveData




    fun SignUp(user:RequestBody) = viewModelScope.launch {
        Log.i("req",user.toString())
        var gson:Gson= Gson()
        var result = remoteRepository.SignUp(user)


        Log.i("res",result.body().toString())
        if (result.isSuccessful) {
            if (result.body() != null) {
                SignUpMutableLiveData.postValue(result.body())
                Log.i("sus",result.body().toString())
            }
    }   else{
            val jsonObj = JSONObject(result.errorBody()!!.charStream().readText())
            SignUpMutableLiveData.postValue(gson.fromJson(jsonObj.toString(), SignupResponse::class.java))
            Log.i("err", jsonObj.toString())
        }
    }

}
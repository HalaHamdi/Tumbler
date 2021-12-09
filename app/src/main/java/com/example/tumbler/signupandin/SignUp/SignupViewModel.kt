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
import kotlinx.coroutines.launch

class SignupViewModel (app: Application) : AndroidViewModel(app) {
    private var remoteRepository: RemoteRepository

    init {
        var serviceInstance = RetroBuilder.getRetroBuilder().create(ServiceAPI::class.java)
        remoteRepository = RemoteRepository(serviceInstance)
    }

    private var SignUpMutableLiveData = MutableLiveData<SignupResponse>()
    val SignUpLiveData: LiveData<SignupResponse> get() = SignUpMutableLiveData


    fun AddUser(user:RequestBody) = viewModelScope.launch {
        Log.i("MOstafa","abbas")
        var result = remoteRepository.SignUp(user)
        Log.i("MOstafa",result.toString())
        if (result.isSuccessful) {
            if (result.body() != null)
                if(result.body()!!.meta.status=="422"|| result.body()!!.meta.status=="500")
                    Log.i("err", result.body()!!.meta.msg)
            else
                SignUpMutableLiveData.postValue(result.body())
        }
    }

}
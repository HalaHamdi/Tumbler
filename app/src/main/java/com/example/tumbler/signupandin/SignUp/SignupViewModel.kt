package com.example.tumbler.signupandin.SignUp

import android.util.Log
import androidx.lifecycle.*
import com.example.tumbler.model.entity.SignUpResponse.RequestBody
import com.example.tumbler.model.entity.SignUpResponse.SignupResponse
import com.example.tumbler.model.network.RemoteRepository
import com.google.gson.Gson
import kotlinx.coroutines.launch
import org.json.JSONObject

class SignupViewModel(private val remoteRepository: RemoteRepository) : ViewModel() {

    private var SignUpMutableLiveData = MutableLiveData<SignupResponse>()
    val SignUpLiveData: LiveData<SignupResponse> get() = SignUpMutableLiveData

    private var SignUpSuccesfulMutableLiveData = MutableLiveData<Int>(0)
    val SignUpSuccesfulLiveData: LiveData<Int> get() = SignUpSuccesfulMutableLiveData

    fun SignUp(user: RequestBody) = viewModelScope.launch {
        Log.i("req", user.toString())
        var gson: Gson = Gson()
        var result = remoteRepository.SignUp(user)

        Log.i("res", result.body().toString())
        if (result.isSuccessful) {
            if (result.body() != null) {
                SignUpMutableLiveData.postValue(result.body())
                Log.i("sus", result.body().toString())
            }
        } else {
            val jsonObj = JSONObject(result.errorBody()!!.charStream().readText())
            SignUpMutableLiveData.postValue(gson.fromJson(jsonObj.toString(), SignupResponse::class.java))
            Log.i("err", jsonObj.toString())
        }
    }
}

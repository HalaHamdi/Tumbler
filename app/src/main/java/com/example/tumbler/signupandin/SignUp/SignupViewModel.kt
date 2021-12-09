package com.example.tumbler.signupandin.SignUp

import android.util.Log
import androidx.lifecycle.*
import com.example.tumbler.model.entity.SignUpResponse.RequestBody
import com.example.tumbler.model.entity.SignUpResponse.SignupResponse
import com.example.tumbler.model.network.RemoteRepository
import kotlinx.coroutines.launch

class SignupViewModel (private val remoteRepository: RemoteRepository) : ViewModel() {

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
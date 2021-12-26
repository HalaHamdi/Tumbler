package com.example.tumbler.userprofile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tumbler.BaseApplication
import com.example.tumbler.model.entity.Meta
import com.example.tumbler.model.entity.createNewTumblr.CreateBlogRequest
import com.example.tumbler.model.network.RemoteRepository
import kotlinx.coroutines.launch

class SettingsViewModel (private val remoteRepository: RemoteRepository) : ViewModel() {
        private var logoutMutableLiveData = MutableLiveData<Meta>()
        val logoutLiveData: LiveData<Meta> get() = logoutMutableLiveData

        fun UserLogout() = viewModelScope.launch {
            var result = remoteRepository.Logout(BaseApplication.user.access_token)
            Log.i("Sobhy", result.toString())

            if (result.isSuccessful) {
                Log.i("Elonsol1", result.body().toString())
                logoutMutableLiveData.postValue(result.body()!!.meta)
            } else {
                Log.i("Elonsol4","error ")
                Log.i("Elonsol5", "error = ${result.message()}")
            }
        }
    }

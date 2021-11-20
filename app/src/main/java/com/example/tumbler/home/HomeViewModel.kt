package com.example.tumbler.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tumbler.model.entity.User
import com.example.tumbler.model.network.RemoteRepository
import com.example.tumbler.model.network.RetroBuilder
import com.example.tumbler.model.network.ServiceAPI
import kotlinx.coroutines.launch

class HomeViewModel(app: Application) : AndroidViewModel(app) {
    private var remoteRepository: RemoteRepository

    init {
        var serviceInstance = RetroBuilder.getRetroBuilder().create(ServiceAPI::class.java)
        remoteRepository = RemoteRepository(serviceInstance)
    }

    private var _usersListMutableLiveData = MutableLiveData<List<User>>()
    val usersListMutableLiveData: LiveData<List<User>> get() = _usersListMutableLiveData

    fun getUsersList() = viewModelScope.launch {
        var result = remoteRepository.getAPIUsers()

        if (result.isSuccessful) {
            if (result.body() != null)
                _usersListMutableLiveData.postValue(result.body())
        } else {
            Log.i("err", result.message())
        }
    }
}

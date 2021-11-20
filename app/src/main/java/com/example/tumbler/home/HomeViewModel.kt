package com.example.tumbler.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tumbler.model.entity.User
import com.example.tumbler.model.entity.postbyid.PostByID
import com.example.tumbler.model.entity.postnotesbyid.PostNotesByID
import com.example.tumbler.model.entity.temp
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

    private var _postByIDMutableLiveData = MutableLiveData<PostByID>()
    val postByIDMutableLiveData: LiveData<PostByID> get() = _postByIDMutableLiveData

    private var _postNotesByIDMutableLiveData = MutableLiveData<PostNotesByID>()
    val postNotesByIDMutableLiveData: LiveData<PostNotesByID> get() = _postNotesByIDMutableLiveData

    private var _tem = MutableLiveData<temp>()
    val tem: LiveData<temp> get() = _tem

    fun gett() = viewModelScope.launch {
        var result = remoteRepository.gettt()

        if (result.isSuccessful) {
            if (result.body() != null)
                _tem.postValue(result.body())
        } else {
            Log.i("err", result.message())
        }
    }

    fun getUsersList() = viewModelScope.launch {
        var result = remoteRepository.getAPIUsers()

        if (result.isSuccessful) {
            if (result.body() != null)
                _usersListMutableLiveData.postValue(result.body())
        } else {
            Log.i("err", result.message())
        }
    }

    fun getPostByID(id: Int) = viewModelScope.launch {
        var result = remoteRepository.getPostByID()
        if (result.isSuccessful) {
            if (result.body() != null)
                _postByIDMutableLiveData.postValue(result.body())
        } else {
            Log.i("err", result.message())
        }
    }

    fun getPostNotesByID(id: Int) = viewModelScope.launch {
        var result = remoteRepository.getPostNotesByID()
        if (result.isSuccessful) {
            if (result.body() != null)
                _postNotesByIDMutableLiveData.postValue(result.body())
        } else {
            Log.i("err", result.message())
        }
    }
}

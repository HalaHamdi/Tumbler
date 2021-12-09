package com.example.tumbler.di

import com.example.tumbler.CreatePostViewModel
import com.example.tumbler.home.HomeViewModel
import com.example.tumbler.model.network.RemoteRepository
import com.example.tumbler.model.network.RemoteRepositoryInterface
import com.example.tumbler.model.network.ServiceAPI
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val Base_URL: String = "https://my-json-server.typicode.com/"

val viewModelModule = module {

    viewModel {
        HomeViewModel(get())
    }
    viewModel{
        CreatePostViewModel(get())
    }
}

val repositoryModule = module{
    single { RemoteRepository(api = get())}
}

val serviceAPIModule = module{
    fun getRetroBuilder(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Base_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { getRetroBuilder()}

    fun getServiceAPIInstance(retrofit: Retrofit): ServiceAPI {
        return retrofit.create(ServiceAPI::class.java)
    }

    single { getServiceAPIInstance(retrofit = get())}
}
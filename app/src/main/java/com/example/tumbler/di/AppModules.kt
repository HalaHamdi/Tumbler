package com.example.tumbler.di

import android.app.Application
import android.content.SharedPreferences
import com.example.tumbler.settings.CreateNewTumblrViewModel
import com.example.tumbler.CreatePostViewModel
import com.example.tumbler.home.HomeViewModel
import com.example.tumbler.model.network.RemoteRepository
import com.example.tumbler.model.network.ServiceAPI
import com.example.tumbler.search.FollowedTagsEditViewModel
import com.example.tumbler.search.SearchViewModel
import com.example.tumbler.signupandin.Login.LoginViewModel
import com.example.tumbler.signupandin.SignUp.SignupViewModel
import com.example.tumbler.userprofile.FollowingViewModel
import com.example.tumbler.userprofile.SettingsViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val Base_URL: String = "https://api.dev.tumbler.social/api/"
// private const val Base_URL: String = "https://mocki.io/v1/"

val viewModelModule = module {

    viewModel {
        HomeViewModel(get())
    }
    viewModel {
        CreatePostViewModel(get())
    }
    viewModel {
        SignupViewModel(get())
    }
    viewModel {
        LoginViewModel(get())
    }

    viewModel {
        CreateNewTumblrViewModel(get())
    }
    viewModel {
        SettingsViewModel(get())
    }

    viewModel {
        SearchViewModel(get())
    }
    viewModel {
        FollowingViewModel(get())
    }
    viewModel {
        FollowedTagsEditViewModel(get())
    }
}

val appModule = module {

    single {
        getSharedPrefs(androidApplication())
    }

    single<SharedPreferences.Editor> {
        getSharedPrefs(androidApplication()).edit()
    }
}

fun getSharedPrefs(androidApplication: Application): SharedPreferences {
    return androidApplication.getSharedPreferences("myshared", 0)
}

val repositoryModule = module {
    single { RemoteRepository(api = get()) }
}

val serviceAPIModule = module {
    fun getRetroBuilder(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Base_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { getRetroBuilder() }

    fun getServiceAPIInstance(retrofit: Retrofit): ServiceAPI {
        return retrofit.create(ServiceAPI::class.java)
    }

    single { getServiceAPIInstance(retrofit = get()) }
}

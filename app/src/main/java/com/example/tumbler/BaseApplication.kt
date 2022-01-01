package com.example.tumbler

import android.app.Application
import com.example.tumbler.di.*
import com.example.tumbler.user.User
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * Base of the Application: first class that application starts with
 * it sets up user module and dependency injection
 */
class BaseApplication : Application() {

    companion object {
        private lateinit var _user: User
        public val user: User get() = _user
    }
//    private lateinit var _user: User
//    public val user :User get() = _user

    fun setUser(
        access_token: String,
        blog_id: Int,
        blog_avatar: String,
        id: Int,
        blog_username: String,
        is_verified: Boolean,
        email: String
    ) {
        _user = User(access_token, blog_id, blog_avatar, id, blog_username, is_verified, email)
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@BaseApplication)
            modules(
                listOf(
                    viewModelModule, repositoryModule, serviceAPIModule, appModule
                )
            )
        }
    }
}

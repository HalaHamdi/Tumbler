package com.example.tumbler

import android.app.Application
import com.example.tumbler.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@BaseApplication)
            modules(listOf(
                viewModelModule, repositoryModule,serviceAPIModule
            ))
        }
    }
}
package com.example.tumbler.model.network
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroBuilder {
    companion object{
        private const val Base_URL:String = "https://my-json-server.typicode.com/"
        fun getRetroBuilder() : Retrofit{
            return Retrofit.Builder()
                .baseUrl(Base_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}
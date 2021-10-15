package com.example.retrofitmvvmexample.network

import com.example.retrofitmvvmexample.util.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var RETROFIT_INSTANCE: Retrofit? = null

    fun getRetrofitClient(): Retrofit {
        //elvis operator ?:
        //if variable is not null, return the variable,
        //otherwise execute the block of code after operator
        return RETROFIT_INSTANCE ?: synchronized(this) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()
            val currentInstance = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
            RETROFIT_INSTANCE = currentInstance
            currentInstance
        }
    }
}
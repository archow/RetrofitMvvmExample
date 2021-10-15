package com.example.retrofitmvvmexample.model

import com.example.retrofitmvvmexample.network.GithubApiService
import com.example.retrofitmvvmexample.network.RetrofitClient
import retrofit2.Call
import retrofit2.Response

class Repository {
    //here we will have functions (and instances of objects if we need them) to
    //make our network calls and data calls
    //so let's have a function that makes our network call and retrieves a response
    suspend fun getGithubResponse(queryParameter: String, sortParameter: String):
            Response<GithubUserResponse?>? {
        val retrofit = RetrofitClient.getRetrofitClient()
        val apiService = retrofit.create(GithubApiService::class.java)
        return apiService.getGithubResponse(queryParameter, sortParameter)
    }

    fun saveData() {
        //this is where you'd update your sharedPreferences
    }
}
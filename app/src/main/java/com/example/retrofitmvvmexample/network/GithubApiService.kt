package com.example.retrofitmvvmexample.network

import com.example.retrofitmvvmexample.model.GithubUserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

//this interface works with the Retrofit object to make a network call
//it basically defines the rest of the url for the endpoints and network requests you will make
interface GithubApiService {
    @GET("search/users")
    suspend fun getGithubResponse(
        @Query("q") queryParameter: String?,
        @Query("sort") sortParameter: String?
    ): Response<GithubUserResponse?>?
}
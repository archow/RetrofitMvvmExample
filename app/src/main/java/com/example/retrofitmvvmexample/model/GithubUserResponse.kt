package com.example.retrofitmvvmexample.model

data class GithubUserResponse(
    val incomplete_results: Boolean,
    val items: List<Item>,
    val total_count: Int
)
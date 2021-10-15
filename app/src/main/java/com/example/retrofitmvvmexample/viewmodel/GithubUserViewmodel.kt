package com.example.retrofitmvvmexample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitmvvmexample.model.Item
import com.example.retrofitmvvmexample.model.Repository
import kotlinx.coroutines.launch

//let's extend the viewmodel class so it can be provided to us in our activity later
//by using a ViewModelProvider
class GithubUserViewmodel(val repository: Repository) : ViewModel() {

    //we want to display a list of items (Github User Items) in our view
    //so we probably want our viewmodel to keep track of that using Livedata
    private val mutableGithubUserLiveData = MutableLiveData<List<Item>>()
    var githubUserLiveData: LiveData<List<Item>> = MutableLiveData<List<Item>>() as LiveData<List<Item>>
        get() = mutableGithubUserLiveData

    init {
        //getGithubUsers()
    }

    fun getGithubUsers(queryParameters: String, sortParameter: String) {
        //we want to perform our network call (or any kind of data retrieval) in a coroutine
        viewModelScope.launch {
            val githubResponse = repository.getGithubResponse(queryParameters, sortParameter)
            if (githubResponse!!.isSuccessful) {
                //we update our livedata here so that whatever observes it will get notified
                //and respond accordingly
                //so...how do?
                    //mutableLivedata has two ways that you can update its value:
                        //set value - updates the livedata in the main thread
                        //post value - updates the livedata in background
//                val listOfUserItems = githubResponse.body()?.items
//                mutableGithubUserLiveData.postValue(listOfUserItems)
                githubResponse.body()?.let {
                    mutableGithubUserLiveData.postValue(it.items)
                }
            } else {
                //some error handling mechanism
            }
        }
    }
}
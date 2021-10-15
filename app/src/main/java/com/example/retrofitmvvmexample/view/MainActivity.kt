package com.example.retrofitmvvmexample.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitmvvmexample.R
import com.example.retrofitmvvmexample.model.Item
import com.example.retrofitmvvmexample.view.adapters.GithubUserAdapter
import com.example.retrofitmvvmexample.viewmodel.GithubUserViewmodel

class MainActivity : AppCompatActivity() {
    private lateinit var mGithubRv: RecyclerView
    private lateinit var mAdapter: GithubUserAdapter
    //let's initialize our viewmodel here
    //...how do?
    val mViewModel by lazy {
        ViewModelProvider(this).get(GithubUserViewmodel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeViews()

        //we have to make sure our activity/view is an observer of our viewmodel's livedata
        //so...how do?
        mViewModel.githubUserLiveData.observe(this,
            {
                //this is where we define how we'll react to our updating of the livedata
                mAdapter = GithubUserAdapter(it)
                mGithubRv.adapter = mAdapter
            })

        //the viewmodel makes a network call and updates the livedata (in this case, our
        //user list livedata)
        mViewModel.getGithubUsers("gero", "repositories")
    }

    fun initializeViews() {
        mGithubRv = findViewById(R.id.github_rv)
        mGithubRv.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
    }
}
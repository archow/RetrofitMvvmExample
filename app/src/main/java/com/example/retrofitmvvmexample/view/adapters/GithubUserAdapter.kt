package com.example.retrofitmvvmexample.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofitmvvmexample.R
import com.example.retrofitmvvmexample.model.Item

class GithubUserAdapter(var users: List<Item>):
    RecyclerView.Adapter<GithubUserViewHolder>() {

    lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubUserViewHolder {
        mContext = parent.context
        val view = LayoutInflater.from(mContext).inflate(R.layout.user_row, parent, false)
        return GithubUserViewHolder(view)
    }

    override fun onBindViewHolder(holder: GithubUserViewHolder, position: Int) {
        val currentUser = users[position]
        holder.userNameTextView.text = currentUser.login
        holder.userIdTextView.text = currentUser.id.toString()
        Glide.with(mContext).load(currentUser.avatar_url)
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.avatarImage)
    }

    override fun getItemCount(): Int {
        return users.size
    }
}

class GithubUserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    var avatarImage: ImageView
    var userNameTextView: TextView
    var userIdTextView: TextView

    init {
        avatarImage = itemView.findViewById(R.id.avatar_image)
        userNameTextView = itemView.findViewById(R.id.username)
        userIdTextView = itemView.findViewById(R.id.id_number)
    }
}
package com.nomkhonwaan.mb.ui.post

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.nomkhonwaan.mb.R.layout.post

class PostAdapter(private val dataSet: Array<Post>) : RecyclerView.Adapter<PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val constraintLayout: ConstraintLayout = LayoutInflater.from(parent.context)
                .inflate(post, parent, false) as ConstraintLayout

        return PostViewHolder(constraintLayout)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.postTitle.text = dataSet[position].title
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

}
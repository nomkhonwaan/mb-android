package com.nomkhonwaan.mb.ui.post

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import com.nomkhonwaan.mb.R.id.post_title

class PostViewHolder(constraintLayout: ConstraintLayout) : RecyclerView.ViewHolder(constraintLayout) {

    val postTitle: TextView = constraintLayout.findViewById(post_title)

}
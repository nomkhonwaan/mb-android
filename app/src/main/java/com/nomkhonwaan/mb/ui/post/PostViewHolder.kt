package com.nomkhonwaan.mb.ui.post

import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.nomkhonwaan.mb.R.id.post_title

class PostViewHolder(constraintLayout: ConstraintLayout) : RecyclerView.ViewHolder(constraintLayout) {

    val postTitle: TextView = constraintLayout.findViewById(post_title)

}
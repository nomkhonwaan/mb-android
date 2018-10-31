package com.nomkhonwaan.mb.ui.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.nomkhonwaan.mb.R.layout.item_nav

class NavItemsAdapter(private val dataSet: Array<NavItem>) : RecyclerView.Adapter<NavItemsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NavItemsViewHolder {
        val textView: TextView = LayoutInflater.from(parent.context)
                .inflate(item_nav, parent, false) as TextView

        return NavItemsViewHolder(textView)
    }

    override fun onBindViewHolder(holder: NavItemsViewHolder, position: Int) {
        holder.textView.text = dataSet[position].name
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

}

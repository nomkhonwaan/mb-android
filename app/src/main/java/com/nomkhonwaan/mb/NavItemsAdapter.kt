package com.nomkhonwaan.mb

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.nomkhonwaan.mb.R.layout.item_nav
import com.nomkhonwaan.mb.models.NavItem

class NavItemsAdapter(private val dataSet: Array<NavItem>) : RecyclerView.Adapter<NavItemsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NavItemsHolder {
        val textView = LayoutInflater.from(parent.context)
                .inflate(item_nav, parent, false) as TextView

        return NavItemsHolder(textView)
    }

    override fun onBindViewHolder(holder: NavItemsHolder, position: Int) {
        holder.textView.text = dataSet[position].name
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

}

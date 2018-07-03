package com.nomkhonwaan.mb

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView

class NavItemsViewAdapter(private val dataSet: Array<NavItem>) : RecyclerView.Adapter<NavItemsViewAdapter.ViewHolder>() {

    class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val textView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_nav, parent, false) as TextView

        return ViewHolder(textView)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = dataSet[position].name
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

}

data class NavItem(val name: String)
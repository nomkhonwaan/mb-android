package com.nomkhonwaan.myblog

import android.content.res.Resources
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_recent_posts.*
import kotlin.math.floor

class RecentPostsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recent_posts)

        btnSidebarToggle.setOnClickListener {
            val params: ViewGroup.MarginLayoutParams = llApp.layoutParams as ViewGroup.MarginLayoutParams
            val marginStart: Int = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (256 * -1).toFloat(), Resources.getSystem().displayMetrics).toInt()
            params.setMargins(if (params.leftMargin != 0) 0 else marginStart, params.topMargin, params.rightMargin, params.bottomMargin)
            llApp.requestLayout()
        }
    }

}
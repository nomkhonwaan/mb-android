package com.nomkhonwaan.myblog

import android.animation.ValueAnimator
import android.content.res.Resources
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_recent_posts.*

class RecentPostsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recent_posts)

        btnSidebarToggle.setOnClickListener {
            val params: ViewGroup.MarginLayoutParams = llApp.layoutParams as ViewGroup.MarginLayoutParams
            val animator: ValueAnimator = ValueAnimator.ofInt(
                    params.leftMargin,
                    if (params.leftMargin != 0) 0 else TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP,
                            (-256).toFloat(),
                            Resources.getSystem().displayMetrics
                    ).toInt()
            )

            animator.addUpdateListener {
                params.leftMargin = it.animatedValue as Int
                llApp.requestLayout()
                Log.d(this.javaClass.name, "${it.animatedValue} ${params.marginStart}")
            }

            animator.duration = 400
            animator.start()
        }
    }

}
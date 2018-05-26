package com.nomkhonwaan.myblog

import android.animation.ValueAnimator
import android.content.res.Resources
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_header.*
import kotlinx.android.synthetic.main.app_sidebar.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sidebarToggleObservable: Observable<Boolean> = Observable.create<Boolean> { emitter ->
            var isCollapsed = true
            val onToggleSidebar = { _: View -> isCollapsed = !isCollapsed; emitter.onNext(isCollapsed) }

            ibSidebarToggle.setOnClickListener(onToggleSidebar)
            tvSidebarClose.setOnClickListener(onToggleSidebar)
            ibSidebarClose.setOnClickListener(onToggleSidebar)
        }

        sidebarToggleObservable.subscribe { isCollapsed ->
            val params: ViewGroup.MarginLayoutParams = llApp.layoutParams as ViewGroup.MarginLayoutParams
            val animator: ValueAnimator = ValueAnimator.ofInt(
                    params.leftMargin,
                    if (isCollapsed) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP,
                            -256f,
                            Resources.getSystem().displayMetrics
                    ).toInt() else 0
            )

            animator.addUpdateListener {
                params.leftMargin = it.animatedValue as Int
                llApp.requestLayout()
            }

            animator.duration = 400
            animator.start()
        }
    }
}

package com.nomkhonwaan.myblog

import android.animation.ValueAnimator
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_header.*
import kotlinx.android.synthetic.main.app_sidebar.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val outMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(outMetrics)

        val llAppParams = llApp.layoutParams
        val inAppSidebarParams = inAppSidebar.layoutParams
        val clAppMainParams = clAppMain.layoutParams

        llAppParams.width = outMetrics.widthPixels + inAppSidebarParams.width
        clAppMainParams.width = outMetrics.widthPixels

        llApp.requestLayout()
        clAppMain.requestLayout()

        val sidebarToggleObservable: Observable<Boolean> = Observable.create { emitter ->
            var isCollapsed = true
            val onToggleSidebar = { _: View -> isCollapsed = !isCollapsed; emitter.onNext(isCollapsed) }

            ibSidebarToggle.setOnClickListener(onToggleSidebar)
            ibSidebarClose.setOnClickListener(onToggleSidebar)
        }

        sidebarToggleObservable.subscribe { isCollapsed ->
            val animator = ValueAnimator.ofFloat(
                    llApp.translationX,
                    TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP,
                            if (isCollapsed) -256f else 0f,
                            outMetrics
                    )
            )

            animator.addUpdateListener {
                llApp.translationX = it.animatedValue as Float
                llApp.requestLayout()
            }

            animator.duration = 400
            animator.start()
        }
    }


}

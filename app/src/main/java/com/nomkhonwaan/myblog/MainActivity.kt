package com.nomkhonwaan.myblog

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
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
            vPopupOverlay.setOnClickListener(onToggleSidebar)
        }

        sidebarToggleObservable.subscribe { isCollapsed ->
            val animatorSet = AnimatorSet()

            animatorSet.playTogether(
                    ObjectAnimator.ofFloat(llApp, "translationX", TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP,
                            if (isCollapsed) -256f else 0f,
                            outMetrics
                    )),
                    ObjectAnimator.ofFloat(vPopupOverlay, "alpha", if (isCollapsed) 0f else 0.16f)
            )

            animatorSet.duration = 400
            animatorSet.start()
        }
    }


}

package com.nomkhonwaan.mb

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.DisplayMetrics
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_header.*
import kotlinx.android.synthetic.main.app_sidebar.*

class MainActivity : AppCompatActivity() {

    private var isCollapsed: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val outMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(outMetrics)

        setAppWidth(outMetrics)
        setSidebarAnimation(outMetrics)

        supportFragmentManager
                .beginTransaction()
                .add(appContent.id, RecentPostsFragment.newInstance())
                .commit()
    }

    private fun setAppWidth(outMetrics: DisplayMetrics) {
        val appParams: ViewGroup.LayoutParams = app.layoutParams
        val appSidebarParams: ViewGroup.LayoutParams = appSidebar.layoutParams
        val appMainParams: ViewGroup.LayoutParams = appMain.layoutParams

        appParams.width = outMetrics.widthPixels + appSidebarParams.width
        appMainParams.width = outMetrics.widthPixels

        app.requestLayout()
        appMain.requestLayout()
    }

    private fun setSidebarAnimation(outMetrics: DisplayMetrics) {
        sidebarObservable().subscribe { isCollapsed ->
            val animatorSet = AnimatorSet()

            if (!isCollapsed) {
                popupOverlay.visibility = View.VISIBLE
            }

            animatorSet.playTogether(
                    ObjectAnimator.ofFloat(app, "translationX", TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP,
                            if (isCollapsed) -256f else 0f,
                            outMetrics
                    )),
                    ObjectAnimator.ofFloat(popupOverlay, "alpha", if (isCollapsed) 0f else 0.16f)
            )

            animatorSet.duration = 400
            animatorSet.addListener(object : Animator.AnimatorListener {
                override fun onAnimationCancel(animation: Animator?) {
                }

                override fun onAnimationEnd(animation: Animator?) {
                    if (isCollapsed) {
                        popupOverlay.visibility = View.GONE
                    }
                }

                override fun onAnimationRepeat(animation: Animator?) {
                }

                override fun onAnimationStart(animation: Animator?) {
                }
            })

            animatorSet.start()
        }
    }

    private fun sidebarObservable(): Observable<Boolean> {
        return Observable.create { emitter ->
            val toggleSidebar = { _: View -> isCollapsed = !isCollapsed; emitter.onNext(isCollapsed) }

            popupOverlay.setOnClickListener(toggleSidebar)
            btnSidebarOpen.setOnClickListener(toggleSidebar)
            btnSidebarClose.setOnClickListener(toggleSidebar)
        }
    }

}

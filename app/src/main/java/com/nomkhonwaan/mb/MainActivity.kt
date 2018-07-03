package com.nomkhonwaan.mb

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.partial_app_header.*
import kotlinx.android.synthetic.main.partial_app_sidebar.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val outMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(outMetrics)

        main.layoutParams.width = outMetrics.widthPixels
        app.layoutParams.width = main.layoutParams.width + sidebar.layoutParams.width

        // Set-up sidebar animation and listen on toggle sidebar events
        setSidebarAnimation(outMetrics)
//
//        setSidebarAnimation(outMetrics)
//        setSidebarNavItems()

//        supportFragmentManager
//                .beginTransaction()
//                .add(appContent.id, RecentPostsFragment.newInstance())
//                .commit()
    }

//    private fun setSidebarNavItems() {
//        val viewManager = LinearLayoutManager(this)
//        val viewAdapter = NavItemsViewAdapter(
//                arrayOf(
//                        NavItem("Home"),
//                        NavItem("Login / Register"),
//                        NavItem("Web Development"),
//                        NavItem("Web Design"),
//                        NavItem("Programming"),
//                        NavItem("DevOps"),
//                        NavItem("Life Style"),
//                        NavItem("How I made this website")
//                )
//        )
//
//        nav.apply {
//            setHasFixedSize(true)
//            layoutManager = viewManager
//            adapter = viewAdapter
//        }
//    }

    private fun setSidebarAnimation(outMetrics: DisplayMetrics) {
        sidebarObservable().subscribe { isCollapsed ->
            val animatorSet = AnimatorSet()

            if (!isCollapsed) {
                popup_overlay.visibility = View.VISIBLE
            }

            animatorSet.playTogether(
                    ObjectAnimator.ofFloat(app, "translationX", TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP,
                            if (isCollapsed) -256f else 0f,
                            outMetrics
                    )),
                    ObjectAnimator.ofFloat(popup_overlay, "alpha", if (isCollapsed) 0f else 0.16f)
            )

            animatorSet.duration = 400
            animatorSet.addListener(object : Animator.AnimatorListener {
                override fun onAnimationCancel(animation: Animator?) {
                }

                override fun onAnimationEnd(animation: Animator?) {
                    if (isCollapsed) {
                        popup_overlay.visibility = View.GONE
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
            var isCollapsed = true
            val toggleSidebar = { _: View -> isCollapsed = !isCollapsed; emitter.onNext(isCollapsed) }

            popup_overlay.setOnClickListener(toggleSidebar)
            button_sidebar_open.setOnClickListener(toggleSidebar)
            button_sidebar_close.setOnClickListener(toggleSidebar)
        }
    }

}

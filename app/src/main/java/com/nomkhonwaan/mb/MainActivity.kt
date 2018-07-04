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

        // Set the main width to equal with a screen width
        main.layoutParams.width = outMetrics.widthPixels

        // Set the app width to equal with a sum of the main width plus sidebar width
        app.layoutParams.width = main.layoutParams.width + sidebar.layoutParams.width

        // Create an animation set for sidebar and popup_overlay on toggle sidebar
        createToggleSidebarAnimation(outMetrics)

        // Add list of nav-items to the sidebar navigation (RecyclerView)
        addSidebarNavItems()

        // Default render a recent-posts page
        renderRecentPosts()
    }

    private fun createToggleSidebarAnimation(outMetrics: DisplayMetrics) {
        toggleSidebarObservable().subscribe { isCollapsed ->
            val animatorSet = AnimatorSet()

            if (!isCollapsed) {
                popup_overlay.visibility = View.VISIBLE
            }

            animatorSet.playTogether(
                    ObjectAnimator.ofFloat(app, "translationX",
                            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, if (isCollapsed) -256f else 0f, outMetrics)),
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

    private fun toggleSidebarObservable(): Observable<Boolean> {
        return Observable.create { emitter ->
            var isCollapsed = true
            val toggleSidebar = { _: View -> isCollapsed = !isCollapsed; emitter.onNext(isCollapsed) }

            popup_overlay.setOnClickListener(toggleSidebar)
            button_sidebar_open.setOnClickListener(toggleSidebar)
            text_sidebar_close.setOnClickListener(toggleSidebar)
            button_sidebar_close.setOnClickListener(toggleSidebar)
        }
    }

    private fun addSidebarNavItems() {
        val viewManager = LinearLayoutManager(this)
        val viewAdapter = NavItemsViewAdapter(arrayOf(
                NavItem(resources.getString(R.string.sidebar_home)),
                NavItem(resources.getString(R.string.sidebar_login_register)),
                NavItem(resources.getString(R.string.sidebar_web_development)),
                NavItem(resources.getString(R.string.sidebar_web_design)),
                NavItem(resources.getString(R.string.sidebar_programming)),
                NavItem(resources.getString(R.string.sidebar_devops)),
                NavItem(resources.getString(R.string.sidebar_life_style)),
                NavItem(resources.getString(R.string.sidebar_how_i_made_this_website))
        ))

        nav.apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    private fun renderRecentPosts() {
        supportFragmentManager
                .beginTransaction()
                .add(content.id, RecentPostsFragment.newInstance())
                .commit()
    }
}

package com.nomkhonwaan.mb.ui.recentupdates

import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.apollographql.apollo.api.Response
import com.nomkhonwaan.mb.LatestPublishedPostsQuery
import com.nomkhonwaan.mb.R.layout.fragment_recent_updates
import com.nomkhonwaan.mb.services.blogging.BloggingService
import com.nomkhonwaan.mb.ui.post.ListOfPostsFragment
import com.nomkhonwaan.mb.ui.post.Post
import dagger.android.support.AndroidSupportInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_recent_updates.*
import javax.inject.Inject

class RecentUpdatesFragment : Fragment() {

    @Inject
    lateinit var bloggingService: BloggingService

    private val compositeDisposable = CompositeDisposable()

    companion object {

        fun newInstance(): RecentUpdatesFragment {
            val recentPostsFragment = RecentUpdatesFragment()
            val bundle = Bundle()

            recentPostsFragment.arguments = bundle

            return recentPostsFragment
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(fragment_recent_updates, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val displayMetrics = DisplayMetrics()
        activity?.also {
            it.windowManager.defaultDisplay.getMetrics(displayMetrics)

            // Force recent updates height to the screen height
            recent_updates.layoutParams.height = displayMetrics.heightPixels - it.header.layoutParams.height
        }

        // Set section title's border bottom width programmatically
        setSectionTitleBorderBottomWidth(text_recent_posts, view_recent_posts_border_bottom)

        // Render the latest list of published posts
        renderLatestPublishedPosts()
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)

        super.onAttach(context)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        compositeDisposable.dispose()
    }

    /**
     * Set border bottom width to 80% of its section title
     *
     * @param {TextView} sectionTitle
     * @param {View}     border
     */
    private fun setSectionTitleBorderBottomWidth(sectionTitle: TextView, border: View) {
        sectionTitle.measure(0, 0)
        border.layoutParams.width = sectionTitle.measuredWidth * 80 / 100
    }

    /**
     * Render a list of published posts ordered by "publishedAt" property at recent posts section.
     */
    private fun renderLatestPublishedPosts() {
        val disposable: Disposable = bloggingService
                .findAllPublishedPosts()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response: Response<LatestPublishedPostsQuery.Data> ->
                    response.data()?.also { data: LatestPublishedPostsQuery.Data ->
                        val posts: List<Post> = data.latestPublishedPosts().map {
                            Post(title = it.title())
                        }

                        // Hide loading animation
                        anim_view_recent_updates_loading.visibility = View.GONE

                        // Display recent updates content
                        scroll_view_recent_updates_content.visibility = View.VISIBLE

                        childFragmentManager
                                .beginTransaction()
                                .add(latest_published_posts.id, ListOfPostsFragment.newInstance(posts.slice(0..4).toTypedArray()))
                                .commit()
                    }
                }

        disposable.addTo(compositeDisposable)
    }

}

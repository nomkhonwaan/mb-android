package com.nomkhonwaan.mb.ui.recentposts

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nomkhonwaan.mb.R.layout.fragment_recent_posts
import com.nomkhonwaan.mb.services.blogging.BloggingService
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class RecentPostsFragment : Fragment() {

    @Inject
    lateinit var bloggingService: BloggingService

    companion object {

        fun newInstance(): RecentPostsFragment {
            val recentPostsFragment = RecentPostsFragment()
            val bundle = Bundle()

            recentPostsFragment.arguments = bundle

            return recentPostsFragment
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(fragment_recent_posts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        bloggingService.findAllPublishedPosts()
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)

        super.onAttach(context)
    }

}

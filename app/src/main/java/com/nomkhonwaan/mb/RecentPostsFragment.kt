package com.nomkhonwaan.mb

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nomkhonwaan.mb.services.BloggingService
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

class RecentPostsFragment : Fragment() {

    companion object {
        fun newInstance(): RecentPostsFragment {
            val recentPostsFragment = RecentPostsFragment()
            val bundle = Bundle()

            recentPostsFragment.arguments = bundle

            return recentPostsFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recent_posts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val service = BloggingService.newInstance()

        service.latestPublishedPosts()
                .subscribeOn(Schedulers.io())
                .subscribe {
                    it.map {
                        Log.d("recent-posts", it?.userId.toString())
                    }
                }
    }

}

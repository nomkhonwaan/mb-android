package com.nomkhonwaan.mb

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nomkhonwaan.mb.services.BloggingService
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

        val retrofit = Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build()
        val service = retrofit.create(BloggingService::class.java)
        val posts = service.latestPublishedPosts().execute().body()
        Log.d("recent-posts", posts?.get(0)?.title)
//        val service = retrofit.create(BloggingService::class.java)
//        val repo = service.latestPublishedPosts()
//        val posts = repo.execute()
//
//        Log.d("recent-posts", posts.body()?.get(0).toString())
    }

    private fun renderLatestPublishedPosts() {

    }

    private fun renderLatestUpdatedCategories() {

    }

}

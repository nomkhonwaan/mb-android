package com.nomkhonwaan.mb


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

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


}

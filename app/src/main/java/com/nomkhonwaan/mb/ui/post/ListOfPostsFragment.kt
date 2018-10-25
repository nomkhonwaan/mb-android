package com.nomkhonwaan.mb.ui.post

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nomkhonwaan.mb.R.layout.fragment_list_of_posts
import kotlinx.android.synthetic.main.fragment_list_of_posts.*

class ListOfPostsFragment : Fragment() {

    companion object {

        const val POSTS: String = "POSTS"

        fun newInstance(posts: Array<Post>): ListOfPostsFragment {
            val listOfPostsFragment = ListOfPostsFragment()
            val bundle = Bundle()
            bundle.putParcelableArray(POSTS, posts)

            listOfPostsFragment.arguments = bundle

            return listOfPostsFragment
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(fragment_list_of_posts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Render a list of posts
        arguments?.getParcelableArray(POSTS)?.filterIsInstance<Post>()?.also {
            renderListOfPosts(it.toTypedArray())
        }
    }

    /**
     * Render a list of posts to the RecyclerView
     *
     * @param {Array<Post>} posts   An array of posts
     */
    private fun renderListOfPosts(posts: Array<Post>) {
        list_of_posts.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = PostAdapter(posts)
        }
    }

}

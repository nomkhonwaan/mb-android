package com.nomkhonwaan.mb.services.blogging

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.api.cache.http.HttpCachePolicy
import com.apollographql.apollo.rx2.Rx2Apollo
import com.nomkhonwaan.mb.LatestPublishedPostsQuery
import io.reactivex.Observable

class BloggingService(private val apolloClient: ApolloClient) {

    /**
     * Return a list of published posts ordered by "publishedAt" property.
     *
     * @return {<Observable<Response<LatestPublishedPostsQuery.Data>>}
     */
    fun findAllPublishedPosts(): Observable<Response<LatestPublishedPostsQuery.Data>> {
        return Rx2Apollo.from(
                apolloClient
                        .query(LatestPublishedPostsQuery.builder().build())
                        .httpCachePolicy(HttpCachePolicy.CACHE_FIRST)
        )
    }

}

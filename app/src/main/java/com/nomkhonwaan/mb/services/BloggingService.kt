package com.nomkhonwaan.mb.services

import com.nomkhonwaan.mb.models.Post
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface BloggingService {

    companion object {

        fun newInstance(): BloggingService {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(JacksonConverterFactory.create())
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .build()

            return retrofit.create(BloggingService::class.java)
        }

    }

    @GET("/posts")
    fun latestPublishedPosts(
            @Query("page[offset]") offset: Int? = 0,
            @Query("page[limit]") limit: Int? = 5,
            @Query("include") include: String? = "authors,categories,tags"
    ): Observable<List<Post?>>

}
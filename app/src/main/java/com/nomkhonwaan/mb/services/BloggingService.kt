package com.nomkhonwaan.mb.services

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//interface BloggingService {
//
//    companion object {
//        fun newInstance(): BloggingService {
//            val retrofit = Retrofit.Builder()
//                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                    .addConverterFactory(JacksonConverterFactory.create())
//                    .baseUrl("https://api.nomkhonwaan.com/")
//                    .build()
//
//            return retrofit.create(BloggingService::class.java)
//        }
//    }
//
////    @GET("/v1/latest-published-posts")
////    fun latestPublishedPosts(
////            @Query("page[offset]") offset: Int? = 0,
////            @Query("page[limit]") limit: Int? = 5,
////            @Query("include") include: String? = "authors,categories,tags"
////    ): Observable<LatestPublishedPosts>
//
//}
package com.nomkhonwaan.mb.services

import com.nomkhonwaan.mb.models.Post
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BloggingService {

    @GET("/posts")
    fun latestPublishedPosts(
            @Query("page[offset]")
            offset: Int? = 0,
            @Query("page[limit]")
            limit: Int? = 5,
            @Query("include")
            include: String? = "authors,categories,tags"
    ): Call<List<Post>>

}
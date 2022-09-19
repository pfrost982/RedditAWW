package ru.gb.redditaww.network

import ru.gb.redditaww.network.json.RedditApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RedditService {

    @GET("/r/aww/hot.json")
    suspend fun fetchPosts(
        @Query("limit") loadSize: Int = 0,
        @Query("after") after: String? = null,
        @Query("before") before: String? = null
    ): Response<RedditApiResponse>
}
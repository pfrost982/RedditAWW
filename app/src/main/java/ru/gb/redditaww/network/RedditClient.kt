package ru.gb.redditaww.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RedditClient {
    companion object {
        private const val BASE_URL = "https://www.reddit.com/"
        private var retrofit: Retrofit? = null

        fun getClient(): Retrofit {
            when (retrofit) {
                null -> retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit as Retrofit
        }
    }
}
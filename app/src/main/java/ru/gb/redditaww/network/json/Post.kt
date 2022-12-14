package ru.gb.redditaww.network.json

import com.google.gson.annotations.SerializedName

data class Post(
    @SerializedName("name")
    val key: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("score")
    val score: Int,
    @SerializedName("author")
    val author: String,
    @SerializedName("num_comments")
    val commentCount: Int,
    @SerializedName("thumbnail")
    val imageURL: String
)

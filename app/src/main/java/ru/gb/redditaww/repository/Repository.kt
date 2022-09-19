package ru.gb.redditaww.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import ru.gb.redditaww.network.json.Post

interface Repository {
    fun getPosts(): LiveData<PagingData<Post>>
}
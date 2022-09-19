package ru.gb.redditaww.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import ru.gb.redditaww.network.RedditService
import ru.gb.redditaww.network.json.Post

class RepositoryImpl(private val apiService: RedditService) : Repository {

    override fun getPosts(): LiveData<PagingData<Post>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = {
                PostsPagingSource(apiService)
            }, initialKey = RedditApiKey(after = null, before = null)
        ).liveData
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 30
    }
}
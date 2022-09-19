package ru.gb.redditaww.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.gb.redditaww.network.RedditService
import ru.gb.redditaww.network.json.Post

class PostsPagingSource(private val apiService: RedditService) :
    PagingSource<RedditApiKey, Post>() {

    override suspend fun load(params: LoadParams<RedditApiKey>): LoadResult<RedditApiKey, Post> {

        return try {
            val position = params.key ?: RedditApiKey(after = null, before = null)
            val postsList = apiService.fetchPosts(
                loadSize = params.loadSize,
                after = position.after,
                before = position.before
            ).body()?.data?.children?.map { it.data }
            val prevKey = RedditApiKey(after = null, before = postsList?.first()?.key)
            val nextKey = RedditApiKey(after = postsList?.last()?.key, before = null)
            LoadResult.Page(data = postsList ?: listOf(), prevKey = prevKey, nextKey = nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<RedditApiKey, Post>): RedditApiKey {
        return RedditApiKey(after = null, before = null)
    }
}
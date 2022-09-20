package ru.gb.redditaww.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.gb.redditaww.network.RedditService
import ru.gb.redditaww.network.json.Post

class PostsPagingSource(private val apiService: RedditService) :
    PagingSource<PagingSourceKey, Post>() {

    override val keyReuseSupported: Boolean = true

    override suspend fun load(params: LoadParams<PagingSourceKey>): LoadResult<PagingSourceKey, Post> {
        return try {
            val currentKey = params.key ?: PagingSourceKey(after = null, before = null)
            val prevKey: PagingSourceKey? = null
            val nextKey: PagingSourceKey?
            val postsList = apiService.fetchPosts(
                loadSize = params.loadSize,
                after = currentKey.after,
                before = currentKey.before
            ).body()?.data?.children?.map { it.data }
            if (postsList != null) {
                if (postsList.isEmpty()) {
                    return LoadResult.Error(Throwable("Empty!!! $currentKey"))
                }
            }
            if (currentKey.after != null || currentKey.before != null) {
                PagingSourceKey(after = null, before = postsList?.first()?.key)
            }
            nextKey = PagingSourceKey(after = postsList?.last()?.key, before = null)
            LoadResult.Page(data = postsList ?: listOf(), prevKey = prevKey, nextKey = nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<PagingSourceKey, Post>): PagingSourceKey? {
        return null
    }
}
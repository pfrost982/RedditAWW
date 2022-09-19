package ru.gb.redditaww

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import ru.gb.redditaww.network.json.Post
import ru.gb.redditaww.repository.Repository

class MainViewModel : ViewModel() {
    private lateinit var repository: Repository

    fun setRepository(repository: Repository) {
        this.repository = repository
    }

    fun getMovieList(): LiveData<PagingData<Post>> {
        return repository.getPosts().cachedIn(viewModelScope)
    }
}
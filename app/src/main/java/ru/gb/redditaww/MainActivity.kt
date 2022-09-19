package ru.gb.redditaww

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import ru.gb.redditaww.databinding.ActivityMainBinding
import ru.gb.redditaww.network.RedditClient
import ru.gb.redditaww.network.RedditService
import ru.gb.redditaww.repository.RepositoryImpl

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val apiService = RedditClient.getClient()
            .create(RedditService::class.java)

        viewModel.setRepository(RepositoryImpl(apiService))

        val adapter = PostsAdapter()
        binding.recyclerView.adapter = adapter

        lifecycleScope.launch {
            viewModel.getMovieList().observe(this@MainActivity) {
                it?.let {
                    adapter.submitData(lifecycle, it)
                }
            }
        }
    }
}
package ru.gb.redditaww

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.gb.redditaww.databinding.ActivityMainBinding
import ru.gb.redditaww.network.RedditClient
import ru.gb.redditaww.network.RedditService

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

        //viewModel.setRepository(Repository_Impl(apiService))
    }
}
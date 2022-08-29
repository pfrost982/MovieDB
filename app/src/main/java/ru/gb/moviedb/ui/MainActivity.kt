package ru.gb.moviedb.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.gb.moviedb.databinding.ActivityMainBinding
import ru.gb.moviedb.repository.RepositoryMovieImpl
import ru.gb.moviedb.retrofit.ApiService
import ru.gb.moviedb.retrofit.ApiService.Companion.BASE_URL

class MainActivity : AppCompatActivity(), ViewModelSaver {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val apiService = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

        viewModel.setRepository(RepositoryMovieImpl(apiService))
    }

    override fun getViewModel() = viewModel
}

fun interface ViewModelSaver {
    fun getViewModel(): MainViewModel
}
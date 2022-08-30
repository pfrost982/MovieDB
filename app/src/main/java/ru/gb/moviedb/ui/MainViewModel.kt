package ru.gb.moviedb.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.gb.moviedb.repository.RepositoryMovie
import ru.gb.moviedb.retrofit.gson.Movie

class MainViewModel : ViewModel() {
    private lateinit var repository: RepositoryMovie
    private var moviesList: MutableList<Movie> = mutableListOf()

    private val _liveDataMoviesList = MutableLiveData<List<Movie>>()
    val liveDataMoviesList: LiveData<List<Movie>> = _liveDataMoviesList
    fun setRepository(repository: RepositoryMovie) {
        this.repository = repository
    }

    fun getData() {
        viewModelScope.launch {
            val list = repository.getTopRated(1)
            moviesList.addAll(list)
            _liveDataMoviesList.postValue(list)
        }
    }
}
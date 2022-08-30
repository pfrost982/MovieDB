package ru.gb.moviedb.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import ru.gb.moviedb.repository.RepositoryMovie
import ru.gb.moviedb.retrofit.gson.Movie

class MainViewModel : ViewModel() {

    private lateinit var repository: RepositoryMovie

    fun setRepository(repository: RepositoryMovie) {
        this.repository = repository
    }

    fun getMovieList(): LiveData<PagingData<Movie>> {
        return repository.getAllMovies().cachedIn(viewModelScope)
    }
}
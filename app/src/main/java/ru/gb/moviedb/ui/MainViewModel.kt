package ru.gb.moviedb.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.launch
import ru.gb.moviedb.entity.MovieInfo
import ru.gb.moviedb.repository.RepositoryMovie
import ru.gb.moviedb.retrofit.gson.Movie

class MainViewModel : ViewModel() {

    private lateinit var repository: RepositoryMovie

    lateinit var movie: Movie

    private val _liveDataMovieInfo = MutableLiveData<MovieInfo>()
    val liveDataMovieInfo: LiveData<MovieInfo> = _liveDataMovieInfo


    fun setRepository(repository: RepositoryMovie) {
        this.repository = repository
    }

    fun getMovieList(): LiveData<PagingData<Movie>> {
        return repository.getAllMovies().cachedIn(viewModelScope)
    }

    fun getMovieInfo(){
        viewModelScope.launch {
            _liveDataMovieInfo.postValue(repository.getMovie(movie.id))
        }
    }
}
package ru.gb.moviedb.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import ru.gb.moviedb.entity.MovieInfo
import ru.gb.moviedb.retrofit.gson.Movie

interface RepositoryMovie {
    suspend fun getMovie(id: Int): MovieInfo
    fun getAllMovies(): LiveData<PagingData<Movie>>
}
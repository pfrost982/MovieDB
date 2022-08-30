package ru.gb.moviedb.repository

import ru.gb.moviedb.entity.MovieInfo
import ru.gb.moviedb.retrofit.gson.Movie

interface RepositoryMovie {
    suspend fun getTopRated(page: Int): List<Movie>
    suspend fun getMovie(id: Int): MovieInfo
}
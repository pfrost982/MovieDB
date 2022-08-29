package ru.gb.moviedb.repository

import ru.gb.moviedb.entity.MovieInfo
import ru.gb.moviedb.retrofit.gson.Result

interface RepositoryMovie {
    suspend fun getTopRated(page: Int): List<Result>
    suspend fun getMovie(id: Int): MovieInfo
}
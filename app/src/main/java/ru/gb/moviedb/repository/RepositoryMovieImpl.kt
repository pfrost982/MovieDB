package ru.gb.moviedb.repository

import ru.gb.moviedb.entity.MovieInfo
import ru.gb.moviedb.retrofit.ApiService
import ru.gb.moviedb.retrofit.gson.Movie

class RepositoryMovieImpl(private val apiService: ApiService) : RepositoryMovie {

    override suspend fun getTopRated(page: Int): List<Movie> {
        return apiService.getTopRated(page = page).results
    }

    override suspend fun getMovie(id: Int): MovieInfo {
        val movieDetails = apiService.getDetailsMovie(id = id)
        val movieCredits = apiService.getCreditsMovie(id = id)
        return MovieInfo(movieDetails, movieCredits)
    }
}
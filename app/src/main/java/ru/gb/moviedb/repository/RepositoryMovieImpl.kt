package ru.gb.moviedb.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import ru.gb.moviedb.entity.MovieInfo
import ru.gb.moviedb.retrofit.ApiService
import ru.gb.moviedb.retrofit.ApiService.Companion.NETWORK_PAGE_SIZE
import ru.gb.moviedb.retrofit.gson.Movie

class RepositoryMovieImpl(private val apiService: ApiService) : RepositoryMovie {

    override suspend fun getMovie(id: Int): MovieInfo {
        val movieDetails = apiService.getDetailsMovie(id = id)
        val movieCredits = apiService.getCreditsMovie(id = id)
        return MovieInfo(movieDetails, movieCredits)
    }

    override fun getAllMovies(): LiveData<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false,
                initialLoadSize = 10
            ),
            pagingSourceFactory = {
                MoviePagingSource(apiService)
            }
            , initialKey = 1
        ).liveData
    }
}
package ru.gb.moviedb.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.gb.moviedb.retrofit.ApiService
import ru.gb.moviedb.retrofit.gson.Movie

class MoviePagingSource(private val apiService: ApiService): PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {

        return try {
            val position = params.key ?: 1
            val moviesList = apiService.getTopRated(page = position).results
            LoadResult.Page(data = moviesList, prevKey = if (position == 1) null else position - 1,
                nextKey = position + 1)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}
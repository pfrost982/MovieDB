package ru.gb.moviedb.retrofit

import ru.gb.moviedb.retrofit.gson.JsonStructureCreditsMovie
import ru.gb.moviedb.retrofit.gson.JsonStructureDetailsMovie
import ru.gb.moviedb.retrofit.gson.JsonStructureTopMovies
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/top_rated")
    suspend fun getTopRated(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") lang: String = LANGUAGE,
        @Query("page") page: Int
    ): JsonStructureTopMovies

    @GET("movie/{movie_id}")
    suspend fun getDetailsMovie(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") lang: String = LANGUAGE
    ): JsonStructureDetailsMovie

    @GET("movie/{movie_id}/credits")
    suspend fun getCreditsMovie(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") lang: String = LANGUAGE
    ): JsonStructureCreditsMovie

    companion object {
        const val API_KEY = "274f828ad283bd634ef4fc1ee4af255f"
        const val LANGUAGE = "ru-RU"
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val BASE_URL_POSTER = "https://image.tmdb.org/t/p/w500"
    }
}
package ru.gb.moviedb.retrofit.gson


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class JsonStructureTopMovies(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
) : Serializable
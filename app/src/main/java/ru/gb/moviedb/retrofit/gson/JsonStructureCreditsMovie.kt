package ru.gb.moviedb.retrofit.gson


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class JsonStructureCreditsMovie(
    @SerializedName("cast")
    val cast: List<Cast>,
    @SerializedName("crew")
    val crew: List<Crew>,
    @SerializedName("id")
    val id: Int
) : Serializable
package ru.gb.moviedb.entity

import ru.gb.moviedb.retrofit.gson.JsonStructureCreditsMovie
import ru.gb.moviedb.retrofit.gson.JsonStructureDetailsMovie

data class MovieInfo (
    val movieInfo: JsonStructureDetailsMovie,
    val movieCredits: JsonStructureCreditsMovie
)
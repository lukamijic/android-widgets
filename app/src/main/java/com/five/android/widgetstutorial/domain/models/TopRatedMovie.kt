package com.five.android.widgetstutorial.domain.models

data class TopRatedMovie(
    val releaseDate: String,
    val popularity: Float,
    val originalTitle: String,
    val title: String,
    val voteAverage: Float,
    val posterPath: String,
    val movieId: Int
)
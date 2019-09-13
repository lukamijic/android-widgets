package com.five.android.widgetstutorial.data.mapper

import com.five.android.widgetstutorial.data.models.ApiTopRatedMovie
import com.five.android.widgetstutorial.domain.models.TopRatedMovie

//default values so there will be no nullables in domain models
private const val DEFAULT_POSTER_PATH = ""
private const val DEFAULT_OVERVIEW = ""
private const val DEFAULT_BACKDROP_PATH = ""
private const val DEFAULT_RUNTIME = 0

class ApiMapperImpl : ApiMapper {

    override fun mapToTopRatedMovie(topRatedMovies: List<ApiTopRatedMovie>): List<TopRatedMovie> = topRatedMovies.map {
        TopRatedMovie(
            it.releaseDate,
            it.popularity,
            it.originalTitle,
            it.title,
            it.voteAverage,
            it.posterPath ?: DEFAULT_POSTER_PATH,
            it.id
        )
    }
}
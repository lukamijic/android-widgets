package com.five.android.widgetstutorial.data.mapper

import com.five.android.widgetstutorial.data.models.ApiTopRatedMovie
import com.five.android.widgetstutorial.domain.models.TopRatedMovie

interface ApiMapper {

    fun mapToTopRatedMovie(topRatedMovies: List<ApiTopRatedMovie>): List<TopRatedMovie>
}
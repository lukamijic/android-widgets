package com.five.android.widgetstutorial.data.repository

import com.five.android.widgetstutorial.data.mapper.ApiMapper
import com.five.android.widgetstutorial.data.service.MovieService
import com.five.android.widgetstutorial.domain.models.TopRatedMovie
import io.reactivex.Single

class MovieRepositoryImpl(
    private val apiMapper: ApiMapper,
    private val movieService: MovieService
) : MovieRepository {

    override fun getTopRatedMovies(): Single<List<TopRatedMovie>> =
        movieService.getTopRatedMovies().map(apiMapper::mapToTopRatedMovie)
}
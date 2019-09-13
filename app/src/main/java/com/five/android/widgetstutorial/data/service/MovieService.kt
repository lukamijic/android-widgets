package com.five.android.widgetstutorial.data.service

import com.five.android.widgetstutorial.data.models.ApiTopRatedMovie
import io.reactivex.Single

private const val API_KEY = "d2b00cc05927175409fe8e98eb5d0d5c"

class MovieService(private val movieApi: MovieApi) {

    fun getTopRatedMovies(): Single<List<ApiTopRatedMovie>> =
        movieApi.getTopRatedMovies(API_KEY).map { it.results }

    fun searchMovies(query: String): Single<List<ApiTopRatedMovie>> =
        movieApi.searchMovies(API_KEY, query).map { it.results }
}
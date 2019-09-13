package com.five.android.widgetstutorial.data.service

import com.five.android.widgetstutorial.data.models.ApiTopRatedMoviesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("3/movie/top_rated")
    fun getTopRatedMovies(@Query("api_key") apiKey: String): Single<ApiTopRatedMoviesResponse>

    @GET("3/search/movie")
    fun searchMovies(@Query("api_key") apiKey: String, @Query("query") query: String): Single<ApiTopRatedMoviesResponse>
}
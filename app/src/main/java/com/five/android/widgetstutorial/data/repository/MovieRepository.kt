package com.five.android.widgetstutorial.data.repository

import com.five.android.widgetstutorial.domain.models.TopRatedMovie
import io.reactivex.Single

interface MovieRepository {
    fun getTopRatedMovies(): Single<List<TopRatedMovie>>
}
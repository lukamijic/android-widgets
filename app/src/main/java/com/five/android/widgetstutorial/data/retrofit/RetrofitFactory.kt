package com.five.android.widgetstutorial.data.retrofit

import com.five.android.widgetstutorial.data.service.MovieApi

interface RetrofitFactory {
    fun getMovieApiInstance(): MovieApi
}
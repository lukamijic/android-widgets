package com.five.android.widgetstutorial.data.retrofit

import com.five.android.widgetstutorial.data.service.MovieApi
import okhttp3.OkHttpClient
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

private const val API_BASE_URL = "https://api.themoviedb.org/"

class RetrofitFactoryImpl(private val okHttpClient: OkHttpClient) : RetrofitFactory {

    override fun getMovieApiInstance(): MovieApi {
        val retrofit = retrofit2.Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()

        return retrofit.create(MovieApi::class.java)
    }
}
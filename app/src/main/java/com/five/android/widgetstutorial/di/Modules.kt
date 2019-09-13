package com.five.android.widgetstutorial.di

import com.five.android.widgetstutorial.BuildConfig
import com.five.android.widgetstutorial.data.mapper.ApiMapper
import com.five.android.widgetstutorial.data.mapper.ApiMapperImpl
import com.five.android.widgetstutorial.data.repository.MovieRepository
import com.five.android.widgetstutorial.data.repository.MovieRepositoryImpl
import com.five.android.widgetstutorial.data.retrofit.RetrofitFactory
import com.five.android.widgetstutorial.data.retrofit.RetrofitFactoryImpl
import com.five.android.widgetstutorial.data.service.MovieService
import com.five.android.widgetstutorial.domain.usecases.GetTopRatedMoviesUseCase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module

val appModule = module(moduleDeclaration = {

    single {
        GetTopRatedMoviesUseCase(movieRepository = get())
    }

    single<RetrofitFactory> { RetrofitFactoryImpl(okHttpClient = get()) }

    single { get<RetrofitFactory>().getMovieApiInstance() }

    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                else HttpLoggingInterceptor.Level.NONE
            })
            .build()
    }

    single {
        MovieService(movieApi = get())
    }

    single<ApiMapper> { ApiMapperImpl() }

    single<MovieRepository> {
        MovieRepositoryImpl(
            apiMapper = get(),
            movieService = get()
        )
    }
})
package com.five.android.widgetstutorial.domain.usecases

import com.five.android.widgetstutorial.data.repository.MovieRepository
import com.five.android.widgetstutorial.domain.models.TopRatedMovie
import io.reactivex.Single

class GetTopRatedMoviesUseCase(private val movieRepository: MovieRepository) : SingleUseCase<List<TopRatedMovie>> {

    override fun execute(): Single<List<TopRatedMovie>> = movieRepository.getTopRatedMovies()
}
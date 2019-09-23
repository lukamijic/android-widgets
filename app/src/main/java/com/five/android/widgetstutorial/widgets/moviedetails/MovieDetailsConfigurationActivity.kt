package com.five.android.widgetstutorial.widgets.moviedetails

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.five.android.widgetstutorial.R
import com.five.android.widgetstutorial.di.appModule
import com.five.android.widgetstutorial.domain.models.TopRatedMovie
import com.five.android.widgetstutorial.domain.usecases.GetTopRatedMoviesUseCase
import com.five.android.widgetstutorial.widgets.moviedetails.adapter.MoviesAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_movie_details_widget_configuration.*
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.koinApplication

class MovieDetailsConfigurationActivity: AppCompatActivity() {

    private val localKoin = koinApplication {
        androidContext(this@MovieDetailsConfigurationActivity)
        modules(appModule)
    }.koin

    private val compositeDisposable = CompositeDisposable()

    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase by localKoin.inject()

    private var adapter =
        MoviesAdapter(this, this::onMovieSelected)

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_movie_details_widget_configuration)
    }

    override fun onStart() {
        super.onStart()
        moviesRecyclerView.adapter = adapter
        moviesRecyclerView.layoutManager = LinearLayoutManager(this)

        compositeDisposable.add(
            getTopRatedMoviesUseCase.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {  movies ->
                    adapter.submitList(movies)
                }
        )
    }

    override fun onPause() {
        compositeDisposable.dispose()
        super.onPause()
    }

    private fun onMovieSelected(topRatedMovie: TopRatedMovie) {
        TODO("Replace this line")
    }
}
package com.five.android.widgetstutorial.widgets.moviedetails

import android.app.Activity
import android.appwidget.AppWidgetManager
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.five.android.widgetstutorial.R
import com.five.android.widgetstutorial.di.appModule
import com.five.android.widgetstutorial.domain.models.TopRatedMovie
import com.five.android.widgetstutorial.domain.usecases.GetTopRatedMoviesUseCase
import com.five.android.widgetstutorial.preferences.Preferences
import com.five.android.widgetstutorial.widgets.moviedetails.adapter.MoviesAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_movie_details_widget_configuration.*
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.koinApplication

private const val BASE_IMAGE_ULR = "https://image.tmdb.org/t/p/w500/"

class MovieDetailsConfigurationActivity: AppCompatActivity() {

    private val localKoin = koinApplication {
        androidContext(this@MovieDetailsConfigurationActivity)
        modules(appModule)
    }.koin

    private val compositeDisposable = CompositeDisposable()

    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase by localKoin.inject()

    private var adapter =
        MoviesAdapter(this, this::onMovieSelected)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        val appWidgetId = intent?.extras?.getInt(
            AppWidgetManager.EXTRA_APPWIDGET_ID,
            AppWidgetManager.INVALID_APPWIDGET_ID
        ) ?: AppWidgetManager.INVALID_APPWIDGET_ID

        if (AppWidgetManager.INVALID_APPWIDGET_ID == appWidgetId) finish()

        val appWidgetManager = AppWidgetManager.getInstance(this)

        Preferences(this).apply {
            setMovieTitleWidget(appWidgetId, topRatedMovie.originalTitle)
            setMoviePosterUlr(appWidgetId, BASE_IMAGE_ULR + topRatedMovie.posterPath.removePrefix("/"))
        }

        MovieDetailsWidgetProvider.updatedAppWidget(this, appWidgetManager, appWidgetId)

        val resultValue = Intent().apply {
            putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
        }
        setResult(Activity.RESULT_OK, resultValue)
        finish()
    }
}
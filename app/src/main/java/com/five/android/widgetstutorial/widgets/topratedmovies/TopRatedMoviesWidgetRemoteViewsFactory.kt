package com.five.android.widgetstutorial.widgets.topratedmovies

import android.content.Context
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import com.five.android.widgetstutorial.di.appModule
import com.five.android.widgetstutorial.domain.models.TopRatedMovie
import com.five.android.widgetstutorial.domain.usecases.GetTopRatedMoviesUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.koinApplication

private const val MAX_MOVIES = 20
private const val IMAGE_SIZE = 256

private const val BASE_IMAGE_ULR = "https://image.tmdb.org/t/p/w500/"

class TopRatedMoviesWidgetRemoteViewsFactory(private val context: Context): RemoteViewsService.RemoteViewsFactory {

    private val localKoin = koinApplication {
        androidContext(context)
        modules(appModule)
    }.koin

    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase by localKoin.inject()

    private var topRatedMovies = listOf<TopRatedMovie>()

    override fun onCreate() { }

    override fun getLoadingView(): RemoteViews {
        TODO("Replace this line")
    }

    override fun getItemId(position: Int): Long {
        TODO("Replace this line")
    }

    override fun onDataSetChanged() {
        TODO("Replace this line")
    }

    override fun hasStableIds(): Boolean {
        TODO("Replace this line")
    }

    override fun getViewAt(position: Int): RemoteViews {
        TODO("Replace this line")
    }

    override fun getCount(): Int {
        TODO("Replace this line")
    }

    override fun getViewTypeCount(): Int {
        TODO("Replace this line")
    }

    override fun onDestroy() { }
}
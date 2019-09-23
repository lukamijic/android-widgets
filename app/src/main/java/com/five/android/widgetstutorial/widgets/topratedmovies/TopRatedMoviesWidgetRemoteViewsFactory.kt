package com.five.android.widgetstutorial.widgets.topratedmovies

import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.five.android.widgetstutorial.R
import com.five.android.widgetstutorial.di.appModule
import com.five.android.widgetstutorial.domain.models.TopRatedMovie
import com.five.android.widgetstutorial.domain.usecases.GetTopRatedMoviesUseCase
import com.five.android.widgetstutorial.util.MOVIE_POSTER_URL
import com.five.android.widgetstutorial.util.MOVIE_TITLE
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

    override fun getLoadingView(): RemoteViews = RemoteViews(context.packageName, R.layout.widget_top_rated_movies_loading_item)

    override fun getItemId(position: Int): Long = topRatedMovies[position].movieId.toLong()

    override fun onDataSetChanged() {
        topRatedMovies = getTopRatedMoviesUseCase.execute().blockingGet()
    }

    override fun hasStableIds(): Boolean = true

    override fun getViewAt(position: Int): RemoteViews = RemoteViews(context.packageName, R.layout.widget_top_rated_movies_item).apply {
        if (topRatedMovies[position].posterPath.isNotEmpty()) {
            val bitmap = Glide.with(context)
                .asBitmap() .load(BASE_IMAGE_ULR + topRatedMovies[position].posterPath.removePrefix("/"))
                .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.RESOURCE))
                .submit(IMAGE_SIZE, IMAGE_SIZE)
                .get()
            setImageViewBitmap(R.id.moviePoster, bitmap)
        } else {
            setImageViewResource(R.id.moviePoster, R.drawable.default_poster)
        }

        setTextViewText(R.id.movieTitle, topRatedMovies[position].originalTitle)
        setTextViewText(R.id.releaseDate, topRatedMovies[position].releaseDate)
        setTextViewText(R.id.averageVote, topRatedMovies[position].voteAverage.toString())

        val fillInIntent = Intent().apply {
            putExtra(MOVIE_TITLE, topRatedMovies[position].originalTitle)
            putExtra(MOVIE_POSTER_URL, BASE_IMAGE_ULR + topRatedMovies[position].posterPath.removePrefix("/"))
        }

        setOnClickFillInIntent(R.id.widgetItem, fillInIntent)
    }

    override fun getCount(): Int = if (topRatedMovies.size > MAX_MOVIES) MAX_MOVIES else topRatedMovies.size

    override fun getViewTypeCount(): Int = 1

    override fun onDestroy() { }
}
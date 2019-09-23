package com.five.android.widgetstutorial.widgets.moviedetails

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.AppWidgetTarget
import com.five.android.widgetstutorial.R
import com.five.android.widgetstutorial.preferences.Preferences

class MovieDetailsWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        appWidgetIds.forEach { updatedAppWidget(context, appWidgetManager, it) }
    }

    override fun onDeleted(context: Context, appWidgetIds: IntArray) {
        val preferences = Preferences(context)
        appWidgetIds.forEach {
            preferences.setMovieTitleWidget(it, null)
            preferences.setMoviePosterUlr(it, null)
        }
    }

    companion object {

        fun updatedAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {
            val views = RemoteViews(context.packageName, R.layout.widget_movie_details)
            val preferences = Preferences(context)

            preferences.getMovieTitleWidget(appWidgetId)?.run { views.setTextViewText(R.id.movieTitle, this) }
            preferences.getMoviePosterUrl(appWidgetId)?.run {
                val appWidgetTarget = AppWidgetTarget(context, R.id.moviePoster, views, appWidgetId)
                Glide.with(context)
                    .asBitmap()
                    .load(this)
                    .into(appWidgetTarget)
            }

            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}
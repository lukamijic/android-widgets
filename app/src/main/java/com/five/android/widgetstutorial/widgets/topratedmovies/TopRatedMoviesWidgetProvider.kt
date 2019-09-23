package com.five.android.widgetstutorial.widgets.topratedmovies

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.view.View
import android.widget.RemoteViews
import com.five.android.widgetstutorial.R

class TopRatedMoviesWidgetProvider: AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        appWidgetIds.forEach { updateAppWidget(context, appWidgetManager, it, appWidgetIds, hasNetworkConnection(context)) }
    }

    private fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, currentAppWidgetId: Int, appWidgetIds: IntArray, hasNetwork: Boolean) {
        val views =  RemoteViews(context.packageName, R.layout.widget_top_rated_movies)
        if (hasNetwork) {
            views.setViewVisibility(R.id.noConnectionMessage, View.INVISIBLE)
            views.setViewVisibility(R.id.moviesList, View.VISIBLE)
            /**
             * Setting up the adapter for ListView
             */
            views.setRemoteAdapter(R.id.moviesList, Intent(context, TopRatedMoviesWidgetRemoteViewsService::class.java))
        } else {
            views.setViewVisibility(R.id.noConnectionMessage, View.VISIBLE)
            views.setViewVisibility(R.id.moviesList, View.INVISIBLE)
        }

        setListItemClick(context, views)
        setRefreshOnClick(context, views, appWidgetIds)

        appWidgetManager.updateAppWidget(currentAppWidgetId, views)
        appWidgetManager.notifyAppWidgetViewDataChanged(currentAppWidgetId, R.id.moviesList)
    }

    private fun setListItemClick(context: Context, views: RemoteViews) {
        val listPendingIntent: PendingIntent = Intent(context, MovieDetailsActivity::class.java).let {
            PendingIntent.getActivity(context, 0, it, 0)
        }

        views.setPendingIntentTemplate(R.id.moviesList, listPendingIntent)
    }

    private fun setRefreshOnClick(context: Context, views: RemoteViews, appWidgetIds: IntArray) {
        val refreshIntent = Intent(context, TopRatedMoviesWidgetProvider::class.java).apply {
            action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
            putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds)
        }

        val pendingIntent = PendingIntent.getBroadcast(context, 0, refreshIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        views.setOnClickPendingIntent(R.id.refreshWidget, pendingIntent)
    }

    private fun hasNetworkConnection(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }
}
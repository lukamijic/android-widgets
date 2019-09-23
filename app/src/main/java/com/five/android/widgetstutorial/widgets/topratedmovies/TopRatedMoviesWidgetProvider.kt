package com.five.android.widgetstutorial.widgets.topratedmovies

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.net.ConnectivityManager
import io.reactivex.Single

class TopRatedMoviesWidgetProvider: AppWidgetProvider() {

    override fun onUpdate(context: Context?, appWidgetManager: AppWidgetManager?, appWidgetIds: IntArray?) {

    }

    private fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, currentAppWidgetId: Int, appWidgetIds: IntArray, hasNetwork: Boolean) {

    }

    //TODO: replace with setRefreshOnClick method

    //TODO: replace with setListItemClick method

    private fun hasNetworkConnection(context: Context): Single<Boolean> =
        Single.fromCallable {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = cm.activeNetworkInfo
            activeNetwork != null && activeNetwork.isConnectedOrConnecting
        }
}
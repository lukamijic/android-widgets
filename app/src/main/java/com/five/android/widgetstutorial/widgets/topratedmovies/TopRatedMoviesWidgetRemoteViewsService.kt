package com.five.android.widgetstutorial.widgets.topratedmovies

import android.content.Intent
import android.widget.RemoteViewsService

class TopRatedMoviesWidgetRemoteViewsService: RemoteViewsService() {

    override fun onGetViewFactory(intent: Intent?): RemoteViewsFactory {
        return TopRatedMoviesWidgetRemoteViewsFactory(applicationContext)
    }
}
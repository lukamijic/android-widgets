package com.five.android.widgetstutorial.widgets.moviedetails

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context

class MovieDetailsWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {

    }

    override fun onDeleted(context: Context, appWidgetIds: IntArray) {

    }

    companion object {

        fun updatedAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {

        }
    }
}
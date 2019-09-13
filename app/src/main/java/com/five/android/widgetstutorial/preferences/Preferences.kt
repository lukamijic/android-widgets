package com.five.android.widgetstutorial.preferences

import android.content.Context
import android.content.SharedPreferences

class Preferences(
    private val context: Context
) {

    companion object {
        private val PREFERENCES_NAME = "preferences"

        private val MOVIE_TITLE_WIDGET = "movie_title"
        private val MOVIE_POSTER_URL = "movie_url"
    }

    private fun getPreferences(): SharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

    fun getMovieTitleWidget(appWidgetId: Int): String? = getPreferences().getString("$MOVIE_TITLE_WIDGET$appWidgetId", null)

    fun setMovieTitleWidget(appWidgetId: Int, value: String?) = getPreferences().edit().putString("$MOVIE_TITLE_WIDGET$appWidgetId", value).apply()

    fun getMoviePosterUrl(appWidgetId: Int): String? = getPreferences().getString("$MOVIE_POSTER_URL$appWidgetId", null)

    fun setMoviePosterUlr(appWidgetId: Int, value: String?) = getPreferences().edit().putString("$MOVIE_POSTER_URL$appWidgetId", value).apply()
}
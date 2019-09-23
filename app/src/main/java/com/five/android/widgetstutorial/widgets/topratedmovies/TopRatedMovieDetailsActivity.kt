package com.five.android.widgetstutorial.widgets.topratedmovies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.five.android.widgetstutorial.R
import com.five.android.widgetstutorial.util.MOVIE_POSTER_URL
import com.five.android.widgetstutorial.util.MOVIE_TITLE
import kotlinx.android.synthetic.main.activity_top_rated_movie_details.*

class MovieDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_rated_movie_details)

        movieTitle.text = intent.getStringExtra(MOVIE_TITLE) ?: ""
        intent.getStringExtra(MOVIE_POSTER_URL)?.run {
            Glide.with(this@MovieDetailsActivity)
                .asBitmap()
                .load(this)
                .into(moviePoster)
        }
    }
}
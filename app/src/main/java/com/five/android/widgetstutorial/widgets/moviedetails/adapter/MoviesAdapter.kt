package com.five.android.widgetstutorial.widgets.moviedetails.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.five.android.widgetstutorial.R
import com.five.android.widgetstutorial.domain.models.TopRatedMovie
import kotlinx.android.synthetic.main.movie_item.view.*

private const val BASE_IMAGE_ULR = "https://image.tmdb.org/t/p/w500/"

class MoviesAdapter(private val context: Context, private val itemClickedAction: (TopRatedMovie) -> Unit): RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    private var topRatedMovies: List<TopRatedMovie> = emptyList()

    fun submitList(data: List<TopRatedMovie>) {
        topRatedMovies = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false))

    override fun getItemCount(): Int = topRatedMovies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.fill(topRatedMovies[position])
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        fun fill(topRatedMovie: TopRatedMovie) {
            topRatedMovie.posterPath.run {
                Glide.with(context)
                    .load(BASE_IMAGE_ULR + this.removePrefix("/"))
                    .into(itemView.moviePoster)
            }

            itemView.movieTitle.text = topRatedMovie.originalTitle
            itemView.setOnClickListener {
                itemClickedAction(topRatedMovie)
            }
        }
    }
}
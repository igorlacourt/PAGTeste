package com.lacourt.pagteste.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lacourt.pagteste.R
import com.lacourt.pagteste.model.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_list_item.view.*

public class TestAdapter(context: Context, list: List<Movie>) : RecyclerView.Adapter<MovieViewHolder>() {
    private var list: List<Movie> = list

    override fun getItemCount(): Int {
        return list.size
    }


    val context = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.movie_list_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie: Movie? = list.get(position)

        holder.title.text = movie?.title
        holder.rate.text = movie?.vote_average.toString()
        holder.genreAndDate.text = "${movie?.genres?.get(0)}, ${movie?.release_date?.subSequence(0,4)}"

        Picasso.get().load("https://image.tmdb.org/t/p/w185/${movie!!.poster_path}")
            .placeholder(R.drawable.clapperboard)
            .into(holder.poster)
    }

    fun submitList(newList: List<Movie>?) {
        if (newList != null) {
            this.list = newList
        }
    }


}


class TestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var poster = itemView.iv_poster
    var title = itemView.title
    var rate = itemView.rating
    var genreAndDate = itemView.genre_date
}

interface OnTestClick {
    fun onMoieClick(movie: Movie)
}
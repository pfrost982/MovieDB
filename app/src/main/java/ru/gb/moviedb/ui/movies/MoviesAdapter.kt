package ru.gb.moviedb.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.gb.moviedb.R
import ru.gb.moviedb.databinding.ItemMovieBinding
import ru.gb.moviedb.retrofit.ApiService.Companion.BASE_URL_POSTER
import ru.gb.moviedb.retrofit.gson.Result

class MoviesAdapter(private val itemListener: OnItemViewClickListener) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    fun interface OnItemViewClickListener {
        fun onClick(position: Int)
    }

    private var moviesList: List<Result> = listOf()

    fun setData(list: List<Result>) {
        moviesList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemMovieBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(moviesList[position])
        holder.itemView.setOnClickListener { itemListener.onClick(position) }
    }

    override fun getItemCount(): Int = moviesList.size

    inner class ViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Result) {
            val urlPoster = BASE_URL_POSTER + movie.posterPath
            Glide.with(this.itemView.context)
                .load(urlPoster)
                .placeholder(R.drawable.ic_movie_poster)
                .into(binding.poster)
            binding.title.text = movie.title
            binding.popularity.text = movie.popularity.toString()
            binding.releaseDate.text = movie.releaseDate
        }
    }
}
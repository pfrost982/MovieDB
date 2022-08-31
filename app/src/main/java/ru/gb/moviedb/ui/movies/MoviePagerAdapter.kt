package ru.gb.moviedb.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.gb.moviedb.R
import ru.gb.moviedb.databinding.ItemMovieBinding
import ru.gb.moviedb.retrofit.ApiService
import ru.gb.moviedb.retrofit.gson.Movie

class MoviePagerAdapter(private val itemListener: OnItemViewClickListener) :
    PagingDataAdapter<Movie, MoviePagerAdapter.MovieViewHolder>(MovieComparator) {

    fun interface OnItemViewClickListener {
        fun onClick(position: Movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(ItemMovieBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)!!
        holder.bind(movie)
        holder.itemView.setOnClickListener { itemListener.onClick(movie) }
    }

    inner class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            val urlPoster = ApiService.BASE_URL_POSTER + movie.posterPath
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

object MovieComparator : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }
}



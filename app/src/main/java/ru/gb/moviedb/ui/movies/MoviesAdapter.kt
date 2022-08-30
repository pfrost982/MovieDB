package ru.gb.moviedb.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.gb.moviedb.R
import ru.gb.moviedb.databinding.ItemMovieBinding
import ru.gb.moviedb.retrofit.ApiService.Companion.BASE_URL_POSTER
import ru.gb.moviedb.retrofit.gson.Movie

class MoviesAdapter(private val itemListener: OnItemViewClickListener) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    fun interface OnItemViewClickListener {
        fun onClick(position: Int)
    }

    private val diffUtil = AsyncListDiffer(this, DiffCallback())

    fun submitList(newList: List<Movie>) {
        diffUtil.submitList(newList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemMovieBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(diffUtil.currentList[position])
        holder.itemView.setOnClickListener { itemListener.onClick(position) }
    }

    override fun getItemCount(): Int = diffUtil.currentList.size

    inner class ViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
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

    private inner class DiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(
            oldItem: Movie,
            newItem: Movie
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: Movie,
            newItem: Movie
        ): Boolean = oldItem.id == newItem.id
    }
}
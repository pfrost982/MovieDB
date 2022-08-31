package ru.gb.moviedb.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.gb.moviedb.R
import ru.gb.moviedb.databinding.ItemCastBinding
import ru.gb.moviedb.retrofit.ApiService.Companion.BASE_URL_POSTER
import ru.gb.moviedb.retrofit.gson.Cast

class CastAdapter : RecyclerView.Adapter<CastAdapter.CastViewHolder>() {

    private var list: List<Cast> = listOf()

    fun submitList(newList: List<Cast>) {
        list = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CastViewHolder(ItemCastBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class CastViewHolder(private val binding: ItemCastBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cast: Cast) {
            binding.castName.text = cast.name
            binding.castRole.text = cast.character
            val urlPoster = BASE_URL_POSTER + cast.profilePath
            Glide.with(this.itemView.context)
                .load(urlPoster)
                .placeholder(R.drawable.ic_actor_photo)
                .into(binding.posterCast)
        }
    }
}
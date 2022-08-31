package ru.gb.moviedb.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import ru.gb.moviedb.R
import ru.gb.moviedb.databinding.FragmentDetailsBinding
import ru.gb.moviedb.retrofit.ApiService
import ru.gb.moviedb.ui.Facade
import ru.gb.moviedb.ui.MainViewModel

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = (requireActivity() as Facade).getViewModel()
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerview.layoutManager = LinearLayoutManager(
            requireContext(), LinearLayoutManager
                .HORIZONTAL, false
        )
        val adapter = CastAdapter()
        binding.recyclerview.adapter = adapter

        viewModel.liveDataMovieInfo.observe(viewLifecycleOwner) { movieInfo ->
            adapter.submitList(movieInfo.movieCredits.cast)
            with(movieInfo.movieInfo) {
                val urlPoster = ApiService.BASE_URL_POSTER + this.posterPath
                Glide.with(binding.poster.context)
                    .load(urlPoster)
                    .placeholder(R.drawable.ic_movie_poster)
                    .into(binding.poster)
                binding.title.text = this.title
                binding.releaseDate.text = this.releaseDate
                val genresString = StringBuilder()
                for (i in genres.indices) {
                    if (i == genres.size - 1) {
                        genresString.append(genres[i].name)
                    } else {
                        genresString.append(genres[i].name).append(", ")
                    }
                }
                binding.genres.text = genresString
                binding.runtime.text = this.runtime.toString() + " мин"
                binding.voteAverage.text = "Рейтинг " + this.voteAverage.toString()
                binding.overview.text = this.overview
            }
        }
        viewModel.getMovieInfo()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
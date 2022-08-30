package ru.gb.moviedb.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import ru.gb.moviedb.R
import ru.gb.moviedb.databinding.FragmentDetailsBinding
import ru.gb.moviedb.databinding.FragmentMoviesBinding
import ru.gb.moviedb.ui.MainViewModel
import ru.gb.moviedb.ui.ViewModelSaver
import ru.gb.moviedb.ui.movies.MoviePagerAdapter

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = (requireActivity() as ViewModelSaver).getViewModel()
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = binding.recyclerview
        recyclerView.layoutManager = LinearLayoutManager(
            requireContext(), LinearLayoutManager
                .VERTICAL, false
        )
        val adapter = MoviePagerAdapter { movie ->
            Toast.makeText(requireActivity(), movie.overview, Toast.LENGTH_LONG).show()
        }
        recyclerView.adapter = adapter

        lifecycleScope.launch {
            viewModel.getMovieList().observe(viewLifecycleOwner) {
                it?.let {
                    adapter.submitData(lifecycle, it)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package ru.gb.moviedb.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import ru.gb.moviedb.databinding.FragmentMoviesBinding
import ru.gb.moviedb.ui.MainViewModel
import ru.gb.moviedb.ui.Facade

class MoviesFragment : Fragment() {

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = (requireActivity() as Facade).getViewModel()
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
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
            (requireActivity() as Facade).openDetailsFragment()
            viewModel.movie = movie
        }

        recyclerView.adapter = adapter

        adapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading ||
                loadState.append is LoadState.Loading
            ) {
                binding.progressBar.isVisible = true
            } else {
                binding.progressBar.isVisible = false
                val errorState = when {
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }
                errorState?.let {
                    Toast.makeText(requireActivity(), it.error.toString(), Toast.LENGTH_LONG).show()
                }
            }
        }

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
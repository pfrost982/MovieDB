package ru.gb.moviedb.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.gb.moviedb.databinding.FragmentMoviesBinding
import ru.gb.moviedb.ui.MainViewModel
import ru.gb.moviedb.ui.ViewModelSaver

class MoviesFragment : Fragment() {

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = (requireActivity() as ViewModelSaver).getViewModel()
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
        val moviesAdapter = MoviesAdapter { position ->
            Toast.makeText(requireActivity(), "Position: $position", Toast.LENGTH_SHORT).show()
        }
        recyclerView.adapter = moviesAdapter
        viewModel.getData()
        viewModel.liveDataMoviesList.observe(viewLifecycleOwner) { moviesAdapter.setData(it) }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
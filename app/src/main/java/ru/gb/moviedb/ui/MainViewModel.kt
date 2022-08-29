package ru.gb.moviedb.ui

import androidx.lifecycle.ViewModel
import ru.gb.moviedb.repository.RepositoryMovie

class MainViewModel : ViewModel() {
    private lateinit var repository: RepositoryMovie

    fun setRepository(repository: RepositoryMovie) {
        this.repository = repository
    }
}
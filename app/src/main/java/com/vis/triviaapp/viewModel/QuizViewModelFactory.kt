package com.vis.triviaapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vis.triviaapp.repository.QuizRepository

class QuizViewModelFactory(val repository: QuizRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return QuizViewModel(repository) as T
    }
}
package com.vis.triviaapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vis.triviaapp.repository.QuizRepository

class QuizViewModelFactory(val repository: QuizRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuizViewModel::class.java)) {
            return QuizViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(SummaryViewModel::class.java)) {
            return SummaryViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            return MainActivityViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(HistoryViewModel::class.java)) {
            return HistoryViewModel(repository) as T
        }


        throw IllegalArgumentException("Unknown class name")
    }
}




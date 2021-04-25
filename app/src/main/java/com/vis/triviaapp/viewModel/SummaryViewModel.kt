package com.vis.triviaapp.viewModel

import androidx.lifecycle.ViewModel
import com.vis.triviaapp.repository.QuizRepository

class SummaryViewModel(private val repository: QuizRepository): ViewModel()  {

    fun getResponseById(userId: Int) = repository.getResponseById(userId)

}
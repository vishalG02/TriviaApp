package com.vis.triviaapp.viewModel

import androidx.lifecycle.ViewModel
import com.vis.triviaapp.model.Question
import com.vis.triviaapp.repository.QuizRepository

class MainActivityViewModel(private val repository: QuizRepository):ViewModel() {

    fun insertQuestionsIntoDb(question: List<Question>) = repository.insertAllQuestion(question)

}
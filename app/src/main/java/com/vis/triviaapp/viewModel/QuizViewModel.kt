package com.vis.triviaapp.viewModel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vis.triviaapp.model.Question
import com.vis.triviaapp.model.Response
import com.vis.triviaapp.repository.QuizRepository

class QuizViewModel(private val repository: QuizRepository) :
    ViewModel() {


    fun insertResponseIntoDb(response: List<Response>) = repository.insertAllResponse(response)


    fun getAllQuestions() = repository.getAllQuestions()


}


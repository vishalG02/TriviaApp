package com.vis.triviaapp.viewModel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vis.triviaapp.model.Question
import com.vis.triviaapp.model.Response
import com.vis.triviaapp.repository.QuizRepository

class QuizViewModel(private val repository: QuizRepository) :
    ViewModel() {


    fun insertQuestionsIntoDb(question: List<Question>) = repository.insertAllQuestion(question)

    fun insertResponseIntoDb(response: List<Response>) = repository.insertAllResponse(response)

    fun getAllResponse() = repository.getAllResponses()


    fun getAllQuestions() = repository.getAllQuestions()
    fun getResponseById(userId: Int) = repository.getResponseById(userId)
    private val responses = MutableLiveData<List<Response>>()


    fun getResponses(): MutableLiveData<List<Response>> {
        responses.postValue(repository.getAllResponses())
        return responses
    }



}


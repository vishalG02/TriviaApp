package com.vis.triviaapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vis.triviaapp.model.Response
import com.vis.triviaapp.repository.QuizRepository

class HistoryViewModel(private val repository: QuizRepository): ViewModel()  {

    private val responses = MutableLiveData<List<Response>>()

    init{
        responses.postValue(repository.getAllResponses())
    }


    fun getResponses(): MutableLiveData<List<Response>> {
        return responses
    }
}
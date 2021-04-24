package com.vis.triviaapp.repository

import com.vis.triviaapp.model.Question
import com.vis.triviaapp.model.Response

interface QuizRepository {
    fun insertQuestion(question: Question)

    fun insertAllQuestion(question: List<Question>)


    fun getAllQuestions(): List<Question>
    fun insertResponse(response: Response)

    fun insertAllResponse(response: List<Response>)

    fun getResponseById(userId: Int): Response

    fun getResponse(): Response

    fun getAllResponses(): List<Response>
}
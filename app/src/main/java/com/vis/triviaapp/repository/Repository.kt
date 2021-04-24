package com.vis.triviaapp.repository

import com.vis.triviaapp.QuizApplication
import com.vis.triviaapp.database.QuizDao
import com.vis.triviaapp.model.Question
import com.vis.triviaapp.model.Response

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class Repository : QuizRepository {
    private val executor: ExecutorService = Executors.newSingleThreadExecutor()

    private val quizDao: QuizDao by lazy {
        QuizApplication.database.quizDao()
    }
    private val getAllQuestions by lazy { quizDao.getAllQuestions() }
    private val getAllResponse by lazy {
        quizDao.getAllResponses()
    }

    override fun insertQuestion(question: Question) {
        executor.execute { quizDao.insertQuestion(question) }
    }

    override fun insertAllQuestion(question: List<Question>) {
        executor.execute { quizDao.insertAllQuestions(question) }
    }

    override fun getAllQuestions(): List<Question> {
        return getAllQuestions
    }

    override fun insertResponse(response: Response) {
        executor.execute { quizDao.insertResponse(response) }
    }

    override fun insertAllResponse(response: List<Response>) {
        executor.execute { quizDao.insertAllResponse(response) }
    }

    override fun getResponseById(userId: Int): Response = quizDao.getResponseById(userId)
    override fun getResponse(): Response = quizDao.getUserId()
    override fun getAllResponses(): List<Response> {
        return getAllResponse
    }


}
package com.vis.triviaapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vis.triviaapp.model.Question
import com.vis.triviaapp.model.Response

@Dao
interface QuizDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertQuestion(question: Question)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertResponse(response: Response)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllResponse(response: List<Response>)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllQuestions(question: List<Question>)

    @Query("SELECT * FROM response where userId=:Id")
    fun getResponseById(Id: Int): Response

    @Query("SELECT * FROM response ")
    fun getUserId(): Response

    @Query("SELECT * FROM response")
    fun getAllResponses(): List<Response>

    @Query("SELECT * FROM question")
    fun getAllQuestions(): List<Question>


}
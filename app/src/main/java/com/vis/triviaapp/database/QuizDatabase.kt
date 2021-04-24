package com.vis.triviaapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vis.triviaapp.model.Question
import com.vis.triviaapp.model.Response
import com.vis.triviaapp.utils.DataConverter

@Database(entities = [(Question::class), (Response::class)], version = 3, exportSchema = false)
abstract class QuizDatabase : RoomDatabase() {
    @TypeConverters(DataConverter::class)

    abstract fun quizDao(): QuizDao


}
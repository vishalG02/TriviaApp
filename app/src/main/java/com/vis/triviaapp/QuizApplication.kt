package com.vis.triviaapp

import android.app.Application
import androidx.room.Room
import com.vis.triviaapp.database.QuizDatabase

class QuizApplication : Application() {

    companion object {
        lateinit var database: QuizDatabase
            private set
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            this, QuizDatabase::class.java,
            "trivia_database"
        ).allowMainThreadQueries().build()
    }

}
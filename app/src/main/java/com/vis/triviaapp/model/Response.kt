package com.vis.triviaapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.vis.triviaapp.utils.DataConverter


@Entity
class Response {


    @PrimaryKey
    var userId: Int = 0
    lateinit var username: String
    lateinit var createDate: String

    @TypeConverters(DataConverter::class)
    lateinit var questionsAndResponse: List<QuestionAndResponse>


}
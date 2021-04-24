package com.vis.triviaapp.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Question(
    @PrimaryKey
    @NonNull
    var question_id: String,
    val question: String? = null,
    val optA: String? = null,
    val optB: String? = null,
    val optC: String? = null,
    val optD: String? = null,
    val textInput: String? = null,
    val multipleAnswerAllowed: Boolean? = null,
    val isTextFieldInput: Boolean? = null


)
package com.vis.triviaapp.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.button.MaterialButton
import com.vis.triviaapp.R
import com.vis.triviaapp.model.Question
import com.vis.triviaapp.repository.Repository
import com.vis.triviaapp.viewModel.QuizViewModel
import com.vis.triviaapp.viewModel.QuizViewModelFactory

class MainActivity : AppCompatActivity() {
    val viewModel: QuizViewModel by lazy {
        ViewModelProviders.of(this, QuizViewModelFactory(Repository())).get(QuizViewModel::class.java)
    }
   // private val viewModel by lazy { QuizViewModel(Repository()) }
    var userId = 1
    var getUserId: Int? = null
    var questionListVariables: ArrayList<Question> = ArrayList()
    val start by lazy {
        findViewById<MaterialButton>(R.id.playButton)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (intent != null)
            getUserId = intent.getIntExtra("id", 0)

        insertQuestions()

        start.setOnClickListener {
            if (getUserId != null && getUserId != 0) {
                val mainIntent = Intent(this@MainActivity, QuizActivity::class.java)
                userId += 2
                mainIntent.putExtra("id", getUserId!! + userId)

                startActivity(mainIntent)
            } else {
                val mainIntent = Intent(this@MainActivity, QuizActivity::class.java)
                userId++
                mainIntent.putExtra("id", userId)

                startActivity(mainIntent)
            }


        }
    }

    private fun insertQuestions() {
        val question1 =
            Question("1", "What is your name?", null, null, null, null, null, false, true)

        val question2 = Question(
            "2",
            "Who is the best cricketer in the world?",
            "Sachin Tendulkar",
            "Virat Kolli",
            "Adam Gilchirst",
            "Jacques Kallis",
            null,
            false,
            false
        )
        val question3 = Question(
            "3",
            "What are the colors in the Indian national flag? Select all:",
            "White",
            "Yellow",
            " Orange",
            "Green",
            null,
            true,
            false
        )
        questionListVariables.add(question1)
        questionListVariables.add(question2)
        questionListVariables.add(question3)
        viewModel.insertQuestionsIntoDb(questionListVariables)

    }


}
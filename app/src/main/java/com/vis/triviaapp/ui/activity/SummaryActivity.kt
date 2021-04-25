package com.vis.triviaapp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.vis.triviaapp.R
import com.vis.triviaapp.model.QuestionAndResponse
import com.vis.triviaapp.model.Response
import com.vis.triviaapp.repository.Repository
import com.vis.triviaapp.viewModel.QuizViewModel
import com.vis.triviaapp.viewModel.QuizViewModelFactory

class SummaryActivity : AppCompatActivity() {
    val viewModel: QuizViewModel by lazy {
        ViewModelProviders.of(this, QuizViewModelFactory(Repository())).get(QuizViewModel::class.java)
    }
   // private val viewModel by lazy { QuizViewModel(Repository()) }
    var responseDetails: Response = Response()
    var allresponseDetails: ArrayList<Response> = ArrayList()

    val tvgame by lazy {
        findViewById<TextView>(R.id.tv_game_sum)
    }
    val tvdate by lazy {
        findViewById<TextView>(R.id.tv_date_sum)
    }
    val tvnameplaceholder by lazy {
        findViewById<TextView>(R.id.tv_name_placeholder_sum)
    }
    val tvquestion1 by lazy {
        findViewById<TextView>(R.id.tv_question1_sum)
    }
    val tvquestion2 by lazy {
        findViewById<TextView>(R.id.tv_question2_sum)
    }
    val tvnamevalue by lazy {
        findViewById<TextView>(R.id.tv_name_value_sum)
    }

    val tvanswer1 by lazy {
        findViewById<TextView>(R.id.tv_answer1_sum)
    }

    val tvanswer2 by lazy {
        findViewById<TextView>(R.id.tv_answer2_sum)
    }
    val btnfinish by lazy {
        findViewById<Button>(R.id.btn_finish)
    }
    val btnhistory by lazy {
        findViewById<Button>(R.id.btn_history)
    }

    var questionAndResponselist: ArrayList<QuestionAndResponse> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summary)

        val userId: Int = intent.getIntExtra("id", 0)

        responseDetails = viewModel.getResponseById(userId)
        allresponseDetails = viewModel.getAllResponse() as ArrayList<Response>
        btnfinish.setOnClickListener(
            {
                val mainIntent = Intent(this, MainActivity::class.java)

                mainIntent.putExtra("id", userId)

                startActivity(mainIntent)
                finish()

            }
        )

        btnhistory.setOnClickListener(
            {
                val mainIntent = Intent(this, HistoryActivity::class.java)

                mainIntent.putExtra("id", userId)

                startActivity(mainIntent)
                finish()
            }
        )
        if (responseDetails != null) {

            tvgame.setText("GAME :" + responseDetails.userId)
            tvnamevalue.setText(responseDetails.username)
            tvdate.setText(responseDetails.createDate)


            for (response in responseDetails.questionsAndResponse) {


                val questionAndResponse = QuestionAndResponse()
                questionAndResponse.question = response.question
                questionAndResponse.optionSelected = response.optionSelected
                questionAndResponse.questionid = response.questionid
                questionAndResponselist.add(questionAndResponse)

            }

            // for(i in 0..questionAndResponselist.size) {
            tvquestion1.setText(questionAndResponselist.get(0).question)
            tvanswer1.setText(questionAndResponselist.get(0).optionSelected)
            tvquestion2.setText(questionAndResponselist.get(1).question)
            tvanswer2.setText(questionAndResponselist.get(1).optionSelected)


            //}


        } else {
            Toast.makeText(applicationContext, "" + allresponseDetails, Toast.LENGTH_SHORT).show()
        }
    }
}
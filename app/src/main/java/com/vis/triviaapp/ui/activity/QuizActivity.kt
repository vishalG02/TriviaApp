package com.vis.triviaapp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.vis.triviaapp.R
import com.vis.triviaapp.model.Question
import com.vis.triviaapp.model.QuestionAndResponse
import com.vis.triviaapp.model.Response
import com.vis.triviaapp.repository.Repository
import com.vis.triviaapp.viewModel.QuizViewModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class QuizActivity : AppCompatActivity() {
    private val viewModel by lazy { QuizViewModel(Repository()) }


    var questionListVariables: List<Question> = ArrayList()
    var responseListVariables: ArrayList<Response> = ArrayList()
    var questionAndresponseListVariables: ArrayList<QuestionAndResponse> = ArrayList()


    val btnnext by lazy {
        findViewById<MaterialButton>(R.id.btnNext)
    }
    val txtInput by lazy {
        findViewById<EditText>(R.id.textInput)
    }
    val txtQuestion by lazy {
        findViewById<TextView>(R.id.question)
    }
    val radioGroup1 by lazy {
        findViewById<RadioGroup>(R.id.radioGroup1)
    }
    val rda by lazy {
        findViewById<RadioButton>(R.id.radio1)
    }
    val rdb by lazy {
        findViewById<RadioButton>(R.id.radio2)
    }

    val rdc by lazy {
        findViewById<RadioButton>(R.id.radio3)
    }
    val rdd by lazy {
        findViewById<RadioButton>(R.id.radio4)
    }
    var response: Response = Response()
    var currentQuestion: Question? = null


    var quidVariables = 0
    var currentQuestionVariables: Question? = null
    var userId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_quiz)
        userId = intent.getIntExtra("id", 0)

        getQuestions()
        setQuestionView()
        btnnext.setOnClickListener(
            {
                performAction()
            }
        )

    }

    private fun setQuestionView() {
        radioGroup1.clearCheck()


        currentQuestion = questionListVariables.get(quidVariables)

        txtQuestion?.setText(currentQuestion!!.question)
        rda?.setText(currentQuestion!!.optA)
        rdb?.setText(currentQuestion!!.optB)
        rdc?.setText(currentQuestion!!.optC)
        rdd?.setText(currentQuestion!!.optD)
        if (currentQuestion!!.isTextFieldInput == true) {
            txtInput?.visibility = View.VISIBLE

        } else {
            txtInput?.visibility = View.GONE
        }

        if (currentQuestion!!.optA.equals(null)) {
            rda!!.visibility = View.GONE
        } else {
            rda!!.visibility = View.VISIBLE
        }
        if (currentQuestion!!.optB.equals(null)) {
            rdb!!.visibility = View.GONE
        } else {
            rdb!!.visibility = View.VISIBLE
        }
        if (currentQuestion!!.optC.equals(null)) {
            rdc!!.visibility = View.GONE
        } else {
            rdc!!.visibility = View.VISIBLE
        }
        if (currentQuestion!!.optD.equals(null)) {
            rdd!!.visibility = View.GONE
        } else {
            rdd!!.visibility = View.VISIBLE
        }


        quidVariables++

    }

    private fun getQuestions() {
        questionListVariables = viewModel.getAllQuestions()
    }


    fun performAction() {
        if (currentQuestion?.isTextFieldInput == true) {
            response.userId = userId!!

            response.username = txtInput?.text.toString()
            response.createDate = getCurrentTime()

            currentQuestionVariables = questionListVariables[quidVariables]
            setQuestionView()
        } else if (txtInput?.text.isNullOrBlank()) {
            Toast.makeText(applicationContext, "Please Enter Value", Toast.LENGTH_SHORT).show()

        } else if (radioGroup1!!.checkedRadioButtonId != -1) {


            val grp = findViewById<View>(R.id.radioGroup1) as RadioGroup
            val answer = findViewById<View>(grp.checkedRadioButtonId) as RadioButton

            val questionAndResponse = QuestionAndResponse()
            questionAndResponse.optionSelected = answer.text as String
            questionAndResponse.questionid = currentQuestion?.question_id!!
            questionAndResponse.question = currentQuestion?.question.toString()


            questionAndresponseListVariables.add(questionAndResponse)

            if (quidVariables < 3) {
                currentQuestionVariables = questionListVariables[quidVariables]
                setQuestionView()
            } else {
                response.questionsAndResponse = questionAndresponseListVariables

                responseListVariables.add(response)

                navigateToEndActivity()
            }

        }else {
            Toast.makeText(applicationContext, "Please select an option", Toast.LENGTH_SHORT).show()
        }
    }

    private fun navigateToEndActivity() {
        viewModel.insertResponseIntoDb(responseListVariables)

        val mainIntent = Intent(this, SummaryActivity::class.java)
        mainIntent.putExtra("id", response.userId)
        startActivity(mainIntent)
        finish()
    }


    val DATE_FORMAT_1 = " dd MMMM yyyy h:mm a"

    fun getCurrentTime(): String {
        val dateFormat = SimpleDateFormat(DATE_FORMAT_1)
        val today = Calendar.getInstance().time
        return dateFormat.format(today)
    }
}
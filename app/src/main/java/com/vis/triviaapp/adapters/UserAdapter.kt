package com.vis.triviaapp.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vis.triviaapp.R
import com.vis.triviaapp.model.QuestionAndResponse
import com.vis.triviaapp.model.Response

class UserAdapter(val responses: ArrayList<Response>, val context: Context) :
    RecyclerView.Adapter<UserAdapter.DataViewHolder>() {
    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bind(responseDetails: Response) {
            val questionAndResponselist: ArrayList<QuestionAndResponse> = ArrayList()

            val tvgame by lazy {
                itemView.findViewById<TextView>(R.id.tv_game)
            }
            val tvdate by lazy {
                itemView.findViewById<TextView>(R.id.tv_date)
            }
            val tvnameplaceholder by lazy {
                itemView.findViewById<TextView>(R.id.tv_name_placeholder)
            }
            val tvquestion1 by lazy {
                itemView.findViewById<TextView>(R.id.tv_question1)
            }
            val tvquestion2 by lazy {
                itemView.findViewById<TextView>(R.id.tv_question2)
            }
            val tvnamevalue by lazy {
                itemView.findViewById<TextView>(R.id.tv_name_value)
            }

            val tvanswer1 by lazy {
                itemView.findViewById<TextView>(R.id.tv_answer1)
            }

            val tvanswer2 by lazy {
                itemView.findViewById<TextView>(R.id.tv_answer2)
            }
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
            tvanswer1.setText("Answer "+questionAndResponselist.get(0).optionSelected)
            tvquestion2.setText(questionAndResponselist.get(1).question)
            tvanswer2.setText("Answer "+questionAndResponselist.get(1).optionSelected)


            //}


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_history, parent,
                false
            )
        )

    override fun getItemCount(): Int = responses.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(responses[position])

    fun addData(response: List<Response>) {
        responses.addAll(response)
    }


}
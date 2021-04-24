package com.vis.triviaapp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vis.triviaapp.R
import com.vis.triviaapp.adapters.UserAdapter
import com.vis.triviaapp.model.Response
import com.vis.triviaapp.repository.Repository
import com.vis.triviaapp.viewModel.QuizViewModel

class HistoryActivity : AppCompatActivity() {

    private val viewModel by lazy { QuizViewModel(Repository()) }
    private lateinit var adapter: UserAdapter

    val recyclerView by lazy {
        findViewById<RecyclerView>(R.id.recyclerView)
    }
    val btnplayagain by lazy {
        findViewById<Button>(R.id.btn_play_again)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        val userId: Int = intent.getIntExtra("id", 0)

        setupUI()
        btnplayagain.setOnClickListener {
            val mainIntent = Intent(this, MainActivity::class.java)

            mainIntent.putExtra("id", userId)

            startActivity(mainIntent)
            finish()
        }


    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter =
            UserAdapter(
                arrayListOf(), this
            )
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
        observeData()
    }

    fun observeData() {
        viewModel.getResponses().observe(this, Observer {
            Log.d("data", it.toString())
            renderList(it as ArrayList<Response>)


        })
    }

    private fun renderList(responses: ArrayList<Response>) {
        adapter.addData(responses)
        adapter.notifyDataSetChanged()
    }


}


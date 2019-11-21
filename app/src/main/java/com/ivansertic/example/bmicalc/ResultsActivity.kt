package com.ivansertic.example.bmicalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_results.*

class ResultsActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        val database = Room.databaseBuilder(
            this,
            ResultDatabase::class.java,
            "results_database"
        ).allowMainThreadQueries()
            .build()


        val allResults = database.resultDao().getAllResults()

        rv_results.apply {
            layoutManager = LinearLayoutManager(this@ResultsActivity)
            adapter = ResultsAdapter(allResults)
        }
    }
}

package com.ivansertic.example.bmicalc

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_results.*
import kotlinx.android.synthetic.main.result_row.view.*

class ResultsAdapter(private val allResults: List<Result>, private val context: Context) : RecyclerView.Adapter<ResultsAdapter.ViewHolder>() {

    private val database = Room.databaseBuilder(
        context,
        ResultDatabase::class.java,
        "results_database"
    ).allowMainThreadQueries()
        .build()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.result_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = allResults.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.tv_result_name.text = allResults[position].name
        holder.view.tv_result_height.text = "Height: ${allResults[position].height}"
        holder.view.tv_result_weight.text = "Weight: ${allResults[position].weight}"
        holder.view.tv_result_bmi.text = allResults[position].calculatedBmi.toString()

        holder.delete.setOnClickListener {

            database.resultDao().deleteResult(allResults[position])

            Log.d("TAG", "delete button clicked with id: ${allResults[position].uid}")
        }


    }

    class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        val delete: Button = view.findViewById(R.id.btn_delete)
    }

}

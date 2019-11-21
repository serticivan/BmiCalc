package com.ivansertic.example.bmicalc

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.result_row.view.*

class ResultsAdapter(private val allResults: List<Result>) : RecyclerView.Adapter<ResultsAdapter.ViewHolder>() {


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


    }

    class ViewHolder(var view: View) : RecyclerView.ViewHolder(view)

}

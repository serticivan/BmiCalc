package com.ivansertic.example.bmicalc

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = Room.databaseBuilder(
            this,
            ResultDatabase::class.java,
            "results_database"
        ).allowMainThreadQueries()
            .build()

        btn_calculate.setOnClickListener {

            if (et_height.text.isNullOrEmpty() || et_weight.text.isNullOrEmpty() || et_name.text.isNullOrEmpty()) {
                tv_result.visibility = View.INVISIBLE
                iv_picture.visibility = View.INVISIBLE

                when {
                    et_height.text.isNullOrEmpty() -> {
                        et_height.error = "Height required"
                        et_height.requestFocus()
                    }
                    et_name.text.isNullOrEmpty() -> {
                        et_name.error = "Name required"
                        et_name.requestFocus()
                    }
                    else -> {
                        et_weight.error = "Height required"
                        et_weight.requestFocus()
                    }
                }

                Toast.makeText(this, "Name, height or weight cannot be empty!!", Toast.LENGTH_LONG)
                    .show()
            } else {

                val name = et_name.text.toString()
                val height = et_height.text.toString().toDouble()
                val weight = et_weight.text.toString().toDouble()
                val result = (weight / (height * height)) * 10000

                if (result in 12.0..70.0) {

                    when (result) {
                        in 12.0..18.4 -> iv_picture.setImageResource(R.drawable.underweight)
                        in 18.5..24.9 -> iv_picture.setImageResource(R.drawable.normal)
                        in 25.0..29.9 -> iv_picture.setImageResource(R.drawable.overweight)
                        in 30.0..34.9 -> iv_picture.setImageResource(R.drawable.obese)
                        in 35.0..70.0 -> iv_picture.setImageResource(R.drawable.extremly_obese)
                    }

                    val rounded = String.format("%.2f", result).toDouble()
                    tv_result.text = "Your BMI: $rounded"
                    tv_result.visibility = View.VISIBLE
                    iv_picture.visibility = View.VISIBLE

                    database.resultDao().insertResult(Result(name = name, height = height, weight = weight, calculatedBmi = rounded))


                } else {
                    tv_result.visibility = View.INVISIBLE
                    iv_picture.visibility = View.INVISIBLE
                    Toast.makeText(
                        this,
                        "Result must be between 12 and 70. Check your weight and height",
                        Toast.LENGTH_LONG
                    ).show()

                }
            }


        }

        btn_show_results.setOnClickListener {
            val intent = Intent(this, ResultsActivity::class.java)
            startActivity(intent)
        }


    }

    private fun calculateBmi() {


    }

}

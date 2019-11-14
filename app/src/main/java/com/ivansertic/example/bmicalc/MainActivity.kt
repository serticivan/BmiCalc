package com.ivansertic.example.bmicalc

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        calculateBmi()


    }

    private fun calculateBmi() {

        btn_calculate.setOnClickListener {

            if (et_height.text.isNullOrEmpty() || et_weight.text.isNullOrEmpty()) {
                tv_result.visibility = View.INVISIBLE
                iv_picture.visibility = View.INVISIBLE

                if (et_height.text.isNullOrEmpty()) {
                    et_height.error = "Height required"
                    et_height.requestFocus()
                } else {
                    et_weight.error = "Height required"
                    et_weight.requestFocus()
                }

                Toast.makeText(this, "Height and weight cannot be empty!!", Toast.LENGTH_LONG)
                    .show()
            } else {

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
    }
}

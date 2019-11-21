package com.ivansertic.example.bmicalc

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Result(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    val name: String,
    val height: Double,
    val weight: Double,
    val calculatedBmi: Double
)
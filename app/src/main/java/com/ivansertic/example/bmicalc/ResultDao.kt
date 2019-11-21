package com.ivansertic.example.bmicalc

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ResultDao {

    @Query("SELECT * FROM result")
    fun getAllResults(): List<Result>

    @Insert
    fun insertResult(result: Result)
}
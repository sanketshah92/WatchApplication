package com.sanket.watchapplication.data.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity(tableName = "heart_rate_history")
data class HeartRateData (
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val heartRate:Int,
    val timestamp: Long
)
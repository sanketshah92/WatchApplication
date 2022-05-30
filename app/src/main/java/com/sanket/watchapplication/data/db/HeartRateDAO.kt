package com.sanket.watchapplication.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sanket.watchapplication.data.models.HeartRateData

@Dao
interface HeartRateDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHeartRate(heartRate: HeartRateData)

    @Query("SELECT * FROM heart_rate_history")
    suspend fun getAllHeartRateData(): List<HeartRateData>

    @Query("DELETE FROM heart_rate_history")
    suspend fun clearHeartRateData()
}
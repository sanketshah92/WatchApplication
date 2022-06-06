package com.sanket.domain

import androidx.lifecycle.LiveData
import com.sanket.domain.models.HeartRateData
import kotlinx.coroutines.flow.Flow


interface HeartRateRepository {
    suspend fun getHeartRateHistoryData(): List<HeartRateData>
    suspend fun createCSVFromHeartRateData(heartRateData: List<HeartRateData>): Flow<Boolean>
    suspend fun deleteHeartRateData():Boolean
    suspend fun addNewHeartRateRecord(heartRate: Int)
    suspend fun getLiveHeartRate():LiveData<HeartRateData>
}
package com.sanket.watchapplication.domain

import com.sanket.watchapplication.data.models.HeartRateData
import kotlinx.coroutines.flow.Flow


interface HeartRateRepository {
    suspend fun getHeartRateHistoryData(): List<HeartRateData>
    suspend fun createCSVFromHeartRateData(heartRateData: List<HeartRateData>): Flow<Boolean>
    suspend fun deleteHeartRateData()
}
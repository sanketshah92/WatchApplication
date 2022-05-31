package com.sanket.watchapplication.data.datasource

import com.sanket.watchapplication.data.models.HeartRateData

interface HeartRateLocalDataSource {
    suspend fun getAllHeartRateDataFromDB(): List<HeartRateData>?
    suspend fun addHeartRateDataToDB(data:HeartRateData)
    suspend fun removeAllHeartRateDataFromDB()
}
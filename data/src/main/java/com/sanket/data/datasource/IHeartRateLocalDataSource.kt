package com.sanket.data.datasource

import androidx.lifecycle.LiveData
import com.sanket.domain.models.HeartRateData

interface IHeartRateLocalDataSource {
    suspend fun getAllHeartRateDataFromDB(): List<HeartRateData>?
    suspend fun addHeartRateDataToDB(data: HeartRateData):Long
    suspend fun removeAllHeartRateDataFromDB():Boolean
    suspend fun getLiveHeartRateFromDB():LiveData<HeartRateData>
}
package com.sanket.data.datasource

import androidx.lifecycle.LiveData
import com.sanket.data.db.HeartRateDAO
import com.sanket.domain.models.HeartRateData

class HeartRateLocalDataSource(private val dao: HeartRateDAO) : IHeartRateLocalDataSource {

    override suspend fun getAllHeartRateDataFromDB(): List<HeartRateData> {
        return dao.getAllHeartRateData()
    }

    override suspend fun addHeartRateDataToDB(data: HeartRateData):Long {
       return dao.insertHeartRate(data)
    }

    override suspend fun removeAllHeartRateDataFromDB(): Boolean {
        return dao.clearHeartRateData() > 0
    }

    override suspend fun getLiveHeartRateFromDB(): LiveData<HeartRateData> {
        return dao.getLiveHeartRateData()
    }
}
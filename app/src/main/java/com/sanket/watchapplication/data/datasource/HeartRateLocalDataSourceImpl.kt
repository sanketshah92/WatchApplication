package com.sanket.watchapplication.data.datasource

import androidx.lifecycle.LiveData
import com.sanket.watchapplication.data.db.HeartRateDAO
import com.sanket.watchapplication.data.models.HeartRateData
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HeartRateLocalDataSourceImpl() : HeartRateLocalDataSource, KoinComponent {
    private val dao: HeartRateDAO by inject()
    override suspend fun getAllHeartRateDataFromDB(): List<HeartRateData> {
        return dao.getAllHeartRateData()
    }

    override suspend fun addHeartRateDataToDB(data: HeartRateData) {
        dao.insertHeartRate(data)
    }

    override suspend fun removeAllHeartRateDataFromDB() {
        dao.clearHeartRateData()
    }

    override suspend fun getLiveHeartRateFromDB(): LiveData<HeartRateData> {
        return dao.getLiveHeartRateData()
    }
}
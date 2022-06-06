package com.sanket.data.datasource

import androidx.lifecycle.LiveData
import com.sanket.data.db.HeartRateDAO
import com.sanket.domain.models.HeartRateData
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

    override suspend fun removeAllHeartRateDataFromDB(): Boolean {
        return dao.clearHeartRateData() > 0
    }

    override suspend fun getLiveHeartRateFromDB(): LiveData<HeartRateData> {
        return dao.getLiveHeartRateData()
    }
}
package com.sanket.data.datasource

import androidx.lifecycle.LiveData
import com.sanket.data.db.HeartRateDAO
import com.sanket.domain.models.HeartRateData
import org.koin.core.component.KoinComponent

class HeartRateLocalDataSourceImpl(private val dao: HeartRateDAO) : HeartRateLocalDataSource, KoinComponent {

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
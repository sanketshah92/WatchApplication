package com.sanket.watchapplication.data.datasource

import com.sanket.watchapplication.data.db.HeartRateDAO
import com.sanket.watchapplication.data.models.HeartRateData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HeartRateLocalDataSourceImpl() : HeartRateLocalDataSource, KoinComponent {
    private val dao: HeartRateDAO by inject()
    override suspend fun getAllHeartRateDataFromDB(): List<HeartRateData> {
        prepareDummyData()
        return dao.getAllHeartRateData()
    }

    private fun prepareDummyData() {
        CoroutineScope(Dispatchers.IO).launch {
            addHeartRateDataToDB(
                HeartRateData(
                    heartRate = 72,
                    timestamp = System.currentTimeMillis(),
                    id = 0
                )
            )
            addHeartRateDataToDB(
                HeartRateData(
                    heartRate = 71,
                    timestamp = System.currentTimeMillis(),
                    id = 0
                )
            )
            addHeartRateDataToDB(
                HeartRateData(
                    heartRate = 70,
                    timestamp = System.currentTimeMillis(),
                    id = 0
                )
            )
            addHeartRateDataToDB(
                HeartRateData(
                    heartRate = 79,
                    timestamp = System.currentTimeMillis(),
                    id = 0
                )
            )
            addHeartRateDataToDB(
                HeartRateData(
                    heartRate = 80,
                    timestamp = System.currentTimeMillis(),
                    id = 0
                )
            )

        }
    }

    override suspend fun addHeartRateDataToDB(data: HeartRateData) {
        dao.insertHeartRate(data)
    }

    override suspend fun removeAllHeartRateDataFromDB() {
        dao.clearHeartRateData()
    }
}
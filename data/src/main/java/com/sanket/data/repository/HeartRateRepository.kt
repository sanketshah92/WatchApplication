package com.sanket.data.repository

import androidx.lifecycle.LiveData
import com.sanket.data.datasource.IHeartRateLocalDataSource
import com.sanket.data.models.CSVExports
import com.sanket.data.models.CsvConfig
import com.sanket.data.models.HeartRateDataCSV
import com.sanket.data.models.toCsv
import com.sanket.data.services.ExportService
import com.sanket.domain.IHeartRateRepository
import com.sanket.domain.models.HeartRateData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch

class HeartRateRepository(private val dataSourceI: IHeartRateLocalDataSource) :
    IHeartRateRepository {

    override suspend fun getHeartRateHistoryData(): List<HeartRateData> {
        return dataSourceI.getAllHeartRateDataFromDB() ?: emptyList()
    }

    override suspend fun createCSVFromHeartRateData(heartRateData: List<HeartRateData>): Flow<Boolean> {
        val result = ExportService.export<HeartRateDataCSV>(
            type = CSVExports.HeartRateCSV(CsvConfig()),
            content = heartRateData.toCsv()
        )
        result.catch { error -> error.printStackTrace() }
        return result
    }

    override suspend fun deleteHeartRateData():Boolean {
        return dataSourceI.removeAllHeartRateDataFromDB()
    }

    override suspend fun addNewHeartRateRecord(heartRate: Int):Long {
        return dataSourceI.addHeartRateDataToDB(
            HeartRateData(
                id = 0,
                heartRate = heartRate,
                timestamp = System.currentTimeMillis()
            )
        )
    }

    override suspend fun getLiveHeartRate(): LiveData<HeartRateData> {
        return dataSourceI.getLiveHeartRateFromDB()
    }
}
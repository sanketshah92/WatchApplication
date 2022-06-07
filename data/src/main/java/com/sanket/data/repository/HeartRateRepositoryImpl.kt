package com.sanket.data.repository

import androidx.lifecycle.LiveData
import com.sanket.data.datasource.HeartRateLocalDataSource
import com.sanket.data.models.CSVExports
import com.sanket.data.models.CsvConfig
import com.sanket.data.models.HeartRateDataCSV
import com.sanket.data.models.toCsv
import com.sanket.data.services.ExportService
import com.sanket.domain.HeartRateRepository
import com.sanket.domain.models.HeartRateData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import org.koin.core.component.KoinComponent

class HeartRateRepositoryImpl(private val dataSource: HeartRateLocalDataSource) :
    HeartRateRepository, KoinComponent {

    override suspend fun getHeartRateHistoryData(): List<HeartRateData> {
        return dataSource.getAllHeartRateDataFromDB() ?: emptyList()
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
        return dataSource.removeAllHeartRateDataFromDB()
    }

    override suspend fun addNewHeartRateRecord(heartRate: Int):Long {
        return dataSource.addHeartRateDataToDB(
            HeartRateData(
                id = 0,
                heartRate = heartRate,
                timestamp = System.currentTimeMillis()
            )
        )
    }

    override suspend fun getLiveHeartRate(): LiveData<HeartRateData> {
        return dataSource.getLiveHeartRateFromDB()
    }
}
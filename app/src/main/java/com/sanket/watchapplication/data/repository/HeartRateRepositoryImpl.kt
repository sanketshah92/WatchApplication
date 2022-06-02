package com.sanket.watchapplication.data.repository

import com.sanket.watchapplication.data.datasource.HeartRateLocalDataSource
import com.sanket.watchapplication.data.models.*
import com.sanket.watchapplication.data.services.ExportService
import com.sanket.watchapplication.domain.HeartRateRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HeartRateRepositoryImpl() :
    HeartRateRepository, KoinComponent {
    private val dataSource: HeartRateLocalDataSource by inject()
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

    override suspend fun deleteHeartRateData() {
        dataSource.removeAllHeartRateDataFromDB()
    }

    override suspend fun addNewHeartRateRecord(heartRate: Int) {
        dataSource.addHeartRateDataToDB(
            HeartRateData(
                id = 0,
                heartRate = heartRate,
                timestamp = System.currentTimeMillis()
            )
        )
    }
}
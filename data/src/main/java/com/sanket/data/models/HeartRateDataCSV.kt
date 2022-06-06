package com.sanket.data.models

import com.opencsv.bean.CsvBindByName
import com.sanket.data.utils.Exportable
import com.sanket.domain.models.HeartRateData

data class HeartRateDataCSV(
    @CsvBindByName(column = "id")
    val id: Int,
    @CsvBindByName(column = "heart_rate")
    val heartRate: Int,
    @CsvBindByName(column = "time")
    val timeStamp: String
) : Exportable

fun List<HeartRateData>.toCsv(): List<HeartRateDataCSV> = map {
    HeartRateDataCSV(
        id = it.id,
        heartRate = it.heartRate,
        timeStamp = it.timestampDateFormat
    )
}
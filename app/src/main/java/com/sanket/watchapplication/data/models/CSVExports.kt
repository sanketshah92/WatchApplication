package com.sanket.watchapplication.data.models

sealed class CSVExports {
    data class HeartRateCSV(val config: CsvConfig) : CSVExports()
}
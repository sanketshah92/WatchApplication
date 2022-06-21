package com.sanket.data.models

sealed class CSVExports {
    data class HeartRateCSV(val config: CsvConfig) : CSVExports()
}
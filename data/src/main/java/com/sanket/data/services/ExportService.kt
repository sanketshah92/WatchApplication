package com.sanket.data.services

import androidx.annotation.WorkerThread
import com.opencsv.CSVWriter
import com.opencsv.bean.StatefulBeanToCsvBuilder
import com.sanket.data.models.CSVExports
import com.sanket.data.models.CsvConfig
import com.sanket.data.utils.Exportable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.File
import java.io.FileWriter

object ExportService {

    fun <T : Exportable> export(type: CSVExports, content: List<T>) : Flow<Boolean> =
      when (type) {
          is CSVExports.HeartRateCSV -> writeToCSV<T>(type.config, content)
      }

    @WorkerThread
    private fun <T : Exportable> writeToCSV(csvConfig: CsvConfig, content: List<T>) =
      flow<Boolean>{
        with(csvConfig) {
            hostPath.ifEmpty { throw IllegalStateException("Wrong Path") }
            val hostDirectory = File(hostPath)
            if (!hostDirectory.exists()) {
                hostDirectory.mkdir()
            }

            val csvFile = File("${hostDirectory.path}/$fileName")
            val csvWriter = CSVWriter(FileWriter(csvFile))

            StatefulBeanToCsvBuilder<T>(csvWriter)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .build()
                .write(content)

            csvWriter.close()
        }
        emit(true)
    }
}

package com.sanket.watchapplication.data.models

import android.os.Environment
import java.text.DateFormat

data class CsvConfig(
    private val prefix: String = "heartrate",
    private val suffix: String = DateFormat
        .getDateTimeInstance()
        .format(System.currentTimeMillis())
        .toString()
        .replace(":",".")
        .replace(",", "")
        .replace(" ", "_"),

    val fileName: String = "$prefix-$suffix.csv",
    @Suppress("DEPRECATION")
    val hostPath: String = Environment
        .getExternalStorageDirectory()?.absolutePath?.plus("/Documents/HeartRate") ?: ""
)
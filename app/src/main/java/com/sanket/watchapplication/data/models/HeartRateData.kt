package com.sanket.watchapplication.data.models


import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.io.Serializable
import java.text.DateFormat


@Entity(tableName = "heart_rate_history")
@Parcelize
data class HeartRateData(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "heart_rate")
    val heartRate: Int,
    @ColumnInfo(name = "time_stamp")
    val timestamp: Long
) : Serializable,Parcelable {
    val timestampDateFormat: String
        get() = DateFormat.getDateTimeInstance().format(timestamp)
}
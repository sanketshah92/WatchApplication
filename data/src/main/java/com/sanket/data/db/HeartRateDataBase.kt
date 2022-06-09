package com.sanket.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sanket.domain.models.HeartRateData

@Database(entities = [HeartRateData::class], version = 1, exportSchema = false)
abstract class HeartRateDataBase:RoomDatabase() {
    abstract fun heartRateDAO(): HeartRateDAO
}
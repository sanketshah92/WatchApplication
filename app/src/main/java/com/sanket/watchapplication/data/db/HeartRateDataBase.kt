package com.sanket.watchapplication.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sanket.watchapplication.data.models.HeartRateData

@Database(entities = [HeartRateData::class], version = 1, exportSchema = false)
abstract class HeartRateDataBase:RoomDatabase() {
    abstract fun heartRateDAO(): HeartRateDAO
}
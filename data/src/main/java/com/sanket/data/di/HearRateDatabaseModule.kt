package com.sanket.data.di

import android.app.Application
import androidx.room.Room
import com.sanket.data.db.HeartRateDAO
import com.sanket.data.db.HeartRateDataBase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val hearRateDatabaseModule = module {
    fun provideHearRateDB(application: Application): HeartRateDataBase {
        return Room.databaseBuilder(application, HeartRateDataBase::class.java, "heart_rate_db")
            .build()
    }

    fun provideHeartRateDAO(dataBase: HeartRateDataBase): HeartRateDAO {
        return dataBase.heartRateDAO()
    }
    single { provideHearRateDB(androidApplication()) }
    single { provideHeartRateDAO(get()) }
}
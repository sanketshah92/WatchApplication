package com.sanket.watchapplication

import android.app.Application
import com.sanket.watchapplication.data.di.hearRateDatabaseModule
import com.sanket.watchapplication.data.di.heartRateDataSourceModule
import com.sanket.watchapplication.data.di.heartRateRepositoryModule
import com.sanket.watchapplication.domain.di.heartRateUseCaseModule
import com.sanket.watchapplication.presentation.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class HeartRateApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@HeartRateApplication)
            modules(listOf(
                hearRateDatabaseModule, heartRateDataSourceModule, heartRateRepositoryModule,
                heartRateUseCaseModule, viewModelModule
            ))
        }
    }
}
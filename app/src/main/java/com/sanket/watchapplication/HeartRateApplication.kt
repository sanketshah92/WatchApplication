package com.sanket.watchapplication

import android.app.Application
import com.sanket.watchapplication.data.di.hearRateDatabaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module


class HeartRateApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@HeartRateApplication)
            module {
                hearRateDatabaseModule
            }
        }
    }
}
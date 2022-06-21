package com.sanket.data.di

import com.sanket.data.datasource.HeartRateLocalDataSource
import com.sanket.data.datasource.IHeartRateLocalDataSource
import org.koin.dsl.module


val heartRateDataSourceModule = module {

    single<IHeartRateLocalDataSource> { HeartRateLocalDataSource(get()) }
}
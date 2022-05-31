package com.sanket.watchapplication.data.di

import com.sanket.watchapplication.data.datasource.HeartRateLocalDataSource
import com.sanket.watchapplication.data.datasource.HeartRateLocalDataSourceImpl
import org.koin.dsl.module


val heartRateDataSourceModule = module {
    factory<HeartRateLocalDataSource> { HeartRateLocalDataSourceImpl() }
}
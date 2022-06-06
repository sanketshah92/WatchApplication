package com.sanket.data.di

import com.sanket.data.datasource.HeartRateLocalDataSource
import com.sanket.data.datasource.HeartRateLocalDataSourceImpl
import org.koin.dsl.module


val heartRateDataSourceModule = module {
    factory<HeartRateLocalDataSource> { HeartRateLocalDataSourceImpl() }
}
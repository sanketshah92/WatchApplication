package com.sanket.watchapplication.data.di

import com.sanket.watchapplication.data.repository.HeartRateRepositoryImpl
import com.sanket.watchapplication.domain.HeartRateRepository
import org.koin.dsl.module


val heartRateRepositoryModule = module {
    factory<HeartRateRepository> { HeartRateRepositoryImpl() }
}
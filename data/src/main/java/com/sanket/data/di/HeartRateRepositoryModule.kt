package com.sanket.data.di

import com.sanket.data.repository.HeartRateRepositoryImpl
import com.sanket.domain.HeartRateRepository
import org.koin.dsl.module


val heartRateRepositoryModule = module {
    factory<HeartRateRepository> { HeartRateRepositoryImpl() }
}
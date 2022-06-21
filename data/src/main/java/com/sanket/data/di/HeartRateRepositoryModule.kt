package com.sanket.data.di

import com.sanket.data.repository.HeartRateRepository
import com.sanket.domain.IHeartRateRepository
import org.koin.dsl.module


val heartRateRepositoryModule = module {
    factory <IHeartRateRepository> { HeartRateRepository(get()) }
}
package com.sanket.watchapplication.domain.di

import com.sanket.watchapplication.domain.usecase.CreateCSVUseCase
import com.sanket.watchapplication.domain.usecase.GetHeartRateHistoryUseCase
import org.koin.dsl.module


val heartRateUseCaseModule = module {
    factory<GetHeartRateHistoryUseCase> { GetHeartRateHistoryUseCase() }
    factory<CreateCSVUseCase> { CreateCSVUseCase() }
}
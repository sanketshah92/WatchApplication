package com.sanket.watchapplication.domain.di

import com.sanket.watchapplication.domain.usecase.CreateCSVUseCase
import com.sanket.watchapplication.domain.usecase.DeleteHeartRateUseCase
import com.sanket.watchapplication.domain.usecase.GetHeartRateHistoryUseCase
import com.sanket.watchapplication.domain.usecase.GetLiveHeartRateUseCase
import org.koin.dsl.module


val heartRateUseCaseModule = module {
    factory<GetHeartRateHistoryUseCase> { GetHeartRateHistoryUseCase() }
    factory<CreateCSVUseCase> { CreateCSVUseCase() }
    factory<DeleteHeartRateUseCase> { DeleteHeartRateUseCase() }
    factory<GetLiveHeartRateUseCase> { GetLiveHeartRateUseCase() }
}
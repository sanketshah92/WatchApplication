package com.sanket.domain.di

import com.sanket.domain.usecase.CreateCSVUseCase
import com.sanket.domain.usecase.DeleteHeartRateUseCase
import com.sanket.domain.usecase.GetHeartRateHistoryUseCase
import com.sanket.domain.usecase.GetLiveHeartRateUseCase
import org.koin.dsl.module


val heartRateUseCaseModule = module {
    factory<GetHeartRateHistoryUseCase> { GetHeartRateHistoryUseCase() }
    factory<CreateCSVUseCase> { CreateCSVUseCase() }
    factory<DeleteHeartRateUseCase> { DeleteHeartRateUseCase() }
    factory<GetLiveHeartRateUseCase> { GetLiveHeartRateUseCase() }
}
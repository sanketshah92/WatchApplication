package com.sanket.domain.usecase

import com.sanket.domain.HeartRateRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetHeartRateHistoryUseCase():KoinComponent {
    private val heartRateRepository: HeartRateRepository by inject()
    suspend fun execute() = heartRateRepository.getHeartRateHistoryData()
}
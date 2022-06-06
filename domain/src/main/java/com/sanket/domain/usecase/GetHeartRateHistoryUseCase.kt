package com.sanket.domain.usecase

import com.sanket.domain.HeartRateRepository
import org.koin.core.component.KoinComponent

class GetHeartRateHistoryUseCase(private val heartRateRepository: HeartRateRepository):KoinComponent {
    suspend fun execute() = heartRateRepository.getHeartRateHistoryData()
}
package com.sanket.domain.usecase

import com.sanket.domain.IHeartRateRepository

class GetHeartRateHistoryUseCase(private val iHeartRateRepository: IHeartRateRepository) {
    suspend fun execute() = iHeartRateRepository.getHeartRateHistoryData()
}
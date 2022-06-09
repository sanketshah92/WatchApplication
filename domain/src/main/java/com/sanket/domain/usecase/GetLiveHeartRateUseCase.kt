package com.sanket.domain.usecase

import com.sanket.domain.IHeartRateRepository


class GetLiveHeartRateUseCase(private val iHeartRateRepository: IHeartRateRepository) {
    suspend fun execute() = iHeartRateRepository.getLiveHeartRate()
}
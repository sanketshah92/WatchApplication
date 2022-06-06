package com.sanket.domain.usecase

import com.sanket.domain.HeartRateRepository
import org.koin.core.component.KoinComponent


class GetLiveHeartRateUseCase(private val heartRateRepository: HeartRateRepository) : KoinComponent {
    suspend fun execute() = heartRateRepository.getLiveHeartRate()
}
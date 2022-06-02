package com.sanket.watchapplication.domain.usecase

import com.sanket.watchapplication.domain.HeartRateRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class GetLiveHeartRateUseCase : KoinComponent {
    private val heartRateRepository: HeartRateRepository by inject()
    suspend fun execute() = heartRateRepository.getLiveHeartRate()
}
package com.sanket.watchapplication.domain.usecase

import com.sanket.watchapplication.domain.HeartRateRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DeleteHeartRateUseCase : KoinComponent {
    private val repository: HeartRateRepository by inject()
    suspend fun execute() = repository.deleteHeartRateData()
}
package com.sanket.domain.usecase

import com.sanket.domain.HeartRateRepository
import org.koin.core.component.KoinComponent

class DeleteHeartRateUseCase(private val repository: HeartRateRepository ) : KoinComponent {
    suspend fun execute() = repository.deleteHeartRateData()
}
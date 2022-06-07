package com.sanket.domain.usecase

import com.sanket.domain.IHeartRateRepository

class DeleteHeartRateUseCase(private val repository: IHeartRateRepository)  {
    suspend fun execute() = repository.deleteHeartRateData()
}
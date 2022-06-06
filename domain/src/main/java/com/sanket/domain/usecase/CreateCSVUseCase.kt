package com.sanket.domain.usecase


import com.sanket.domain.HeartRateRepository
import com.sanket.domain.models.HeartRateData
import org.koin.core.component.KoinComponent

class CreateCSVUseCase(private val repository: HeartRateRepository) : KoinComponent {
    suspend fun execute(heartRateData: List<HeartRateData>) =
        repository.createCSVFromHeartRateData(heartRateData)

}
package com.sanket.domain.usecase


import com.sanket.domain.HeartRateRepository
import com.sanket.domain.models.HeartRateData
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CreateCSVUseCase : KoinComponent {
    private val repository: HeartRateRepository by inject()

    suspend fun execute(heartRateData: List<HeartRateData>) =
        repository.createCSVFromHeartRateData(heartRateData)

}
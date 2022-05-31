package com.sanket.watchapplication.domain.usecase

import com.sanket.watchapplication.data.models.HeartRateData
import com.sanket.watchapplication.domain.HeartRateRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CreateCSVUseCase : KoinComponent {
    private val repository: HeartRateRepository by inject()

    suspend fun execute(heartRateData: List<HeartRateData>) =
        repository.createCSVFromHeartRateData(heartRateData)

}
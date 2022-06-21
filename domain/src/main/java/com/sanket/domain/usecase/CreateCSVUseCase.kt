package com.sanket.domain.usecase


import com.sanket.domain.IHeartRateRepository
import com.sanket.domain.models.HeartRateData

class CreateCSVUseCase(private val repository: IHeartRateRepository) {
    suspend fun execute(heartRateData: List<HeartRateData>) =
        repository.createCSVFromHeartRateData(heartRateData)

}
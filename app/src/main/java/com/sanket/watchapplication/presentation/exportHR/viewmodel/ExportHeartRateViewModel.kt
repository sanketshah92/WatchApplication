package com.sanket.watchapplication.presentation.exportHR.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.sanket.domain.usecase.GetHeartRateHistoryUseCase
import org.koin.core.component.KoinComponent


class ExportHeartRateViewModel(private val getHeartRateHistoryUseCase: GetHeartRateHistoryUseCase) :
    ViewModel(), KoinComponent {
    fun fetchUserHeartRateData() = liveData {
        var heartRateData = getHeartRateHistoryUseCase.execute()
        if (heartRateData.isNotEmpty()) {
            heartRateData.filter { it.heartRate >= 0 }
        } else {
            heartRateData = emptyList()
        }
        emit(heartRateData)
    }


}
package com.sanket.watchapplication.presentation.measureHR.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.sanket.domain.usecase.GetLiveHeartRateUseCase

class MeasureHeartRateViewModel(private val getLiveHeartRateUseCase: GetLiveHeartRateUseCase) :
    ViewModel() {

    fun listenHeartRate() = liveData {
        emit(getLiveHeartRateUseCase.execute())
    }
}
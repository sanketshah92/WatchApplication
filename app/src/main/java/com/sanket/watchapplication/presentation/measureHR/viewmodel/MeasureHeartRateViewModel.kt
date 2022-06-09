package com.sanket.watchapplication.presentation.measureHR.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.sanket.domain.usecase.GetLiveHeartRateUseCase
import org.koin.core.component.KoinComponent

class MeasureHeartRateViewModel(private val getLiveHeartRateUseCase: GetLiveHeartRateUseCase) :
    ViewModel(), KoinComponent {


    fun listenHeartRate() = liveData {
        emit(getLiveHeartRateUseCase.execute())
    }
}
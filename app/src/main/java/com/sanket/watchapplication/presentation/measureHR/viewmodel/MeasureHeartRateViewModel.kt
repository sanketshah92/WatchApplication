package com.sanket.watchapplication.presentation.measureHR.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.sanket.watchapplication.domain.usecase.GetLiveHeartRateUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MeasureHeartRateViewModel : ViewModel(), KoinComponent {
    private val getLiveHeartRateUseCase: GetLiveHeartRateUseCase by inject()

    fun listenHeartRate() = liveData {
        emit(getLiveHeartRateUseCase.execute())
    }
}
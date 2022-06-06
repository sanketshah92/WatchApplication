package com.sanket.watchapplication.presentation.exportHR.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.sanket.domain.usecase.GetHeartRateHistoryUseCase


import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class ExportHeartRateViewModel() : ViewModel(), KoinComponent {
    private val getHeartRateHistoryUseCase: GetHeartRateHistoryUseCase by inject()
    fun fetchUserHeartRateData() = liveData {
        val heartRateData = getHeartRateHistoryUseCase.execute()
        emit(heartRateData)
    }
}
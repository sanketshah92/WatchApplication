package com.sanket.watchapplication.presentation.exportHR.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.sanket.watchapplication.data.models.HeartRateData
import com.sanket.watchapplication.domain.usecase.CreateCSVUseCase
import com.sanket.watchapplication.domain.usecase.DeleteHeartRateUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HeartRateToCSVViewModel : ViewModel(), KoinComponent {
    private val createCsvUseCase: CreateCSVUseCase by inject()
    private val deleteHeartRateUseCase: DeleteHeartRateUseCase by inject()

    val exportState = MutableLiveData<String>()

    fun prepareCSV(data: List<HeartRateData>) {
        viewModelScope.launch(Dispatchers.IO) {
            exportState.postValue("Loading")
            createCsvUseCase.execute(data).catch { error ->
                exportState.postValue("ERROR")
                error.printStackTrace()
            }.collect { isSuccess ->
                if (isSuccess) {
                    delay(2500)
                    exportState.postValue("SUCCESS")
                }
            }
        }
    }

    fun removePastRecords() = liveData<Boolean> {
        deleteHeartRateUseCase.execute()
        emit(true)
    }
}
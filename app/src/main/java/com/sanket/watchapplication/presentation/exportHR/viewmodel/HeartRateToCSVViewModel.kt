package com.sanket.watchapplication.presentation.exportHR.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.sanket.domain.models.HeartRateData
import com.sanket.domain.usecase.CreateCSVUseCase
import com.sanket.domain.usecase.DeleteHeartRateUseCase
import com.sanket.watchapplication.presentation.utils.ERROR
import com.sanket.watchapplication.presentation.utils.LOADING
import com.sanket.watchapplication.presentation.utils.SUCCESS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HeartRateToCSVViewModel(private val createCsvUseCase: CreateCSVUseCase,private val deleteHeartRateUseCase: DeleteHeartRateUseCase) : ViewModel() {

    val exportState = MutableLiveData<String>()

    fun prepareCSV(data: List<HeartRateData>) {
        viewModelScope.launch(Dispatchers.IO) {
            exportState.postValue(LOADING)
            createCsvUseCase.execute(data).catch { error ->
                exportState.postValue(ERROR)
                error.printStackTrace()
            }.collect { isSuccess ->
                if (isSuccess) {
                    delay(2500)
                    exportState.postValue(SUCCESS)
                }
            }
        }
    }

    fun removePastRecords() = liveData<Boolean> {
        deleteHeartRateUseCase.execute()
        emit(true)
    }
}
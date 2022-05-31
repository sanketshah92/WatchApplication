package com.sanket.watchapplication.presentation.exportHR.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanket.watchapplication.data.models.HeartRateData
import com.sanket.watchapplication.domain.usecase.CreateCSVUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HeartRateToCSVViewModel : ViewModel(), KoinComponent {
    private val createCsvUseCase: CreateCSVUseCase by inject()
    val exportState = MutableLiveData<String>()

    fun prepareCSV(data: List<HeartRateData>) {
        viewModelScope.launch(Dispatchers.IO) {
            exportState.postValue("Loading")
            createCsvUseCase.execute(data).catch { error ->
                exportState.postValue("ERROR")
                error.printStackTrace()
            }.collect {
                exportState.postValue("SUCCESS")
            }
        }
    }
}
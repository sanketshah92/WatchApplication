package com.sanket.watchapplication.presentation.exportHR.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.google.common.truth.Truth.assertThat
import com.sanket.domain.models.HeartRateData
import com.sanket.domain.usecase.CreateCSVUseCase
import com.sanket.domain.usecase.DeleteHeartRateUseCase
import com.sanket.watchapplication.utils.mock
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HeartRateToCSVViewModelTest {
    @DelicateCoroutinesApi
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private val createCsvUseCase: CreateCSVUseCase = mock()
    private val deleteHeartRateUseCase: DeleteHeartRateUseCase = mock()
    private val heartRateToCSVViewModel: HeartRateToCSVViewModel by lazy {
        HeartRateToCSVViewModel(createCsvUseCase, deleteHeartRateUseCase)
    }

    @ExperimentalCoroutinesApi
    @Before
    fun init() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @Test
    fun `prepare CSV with heart rate data`() {
        val heartRateData = mutableListOf<HeartRateData>()
        heartRateData.add(
            HeartRateData(
                id = 0,
                heartRate = 72,
                timestamp = System.currentTimeMillis()
            )
        )
        heartRateToCSVViewModel.prepareCSV(heartRateData)
        heartRateToCSVViewModel.exportState.observeForever(Observer {
            assertThat(it).isEqualTo("SUCCESS")
        })
    }

    @Test
    fun `prepare CSV without heart rate data`() {
        heartRateToCSVViewModel.prepareCSV(emptyList())
        heartRateToCSVViewModel.exportState.observeForever(Observer {
            assertThat(it).isEqualTo("FAILURE")
        })
    }

    @Test
    fun `remove exported heart rate data`(){
        heartRateToCSVViewModel.removePastRecords().observeForever(Observer {
            assertThat(it).isTrue()
        })
    }

    @ExperimentalCoroutinesApi
    @DelicateCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }
}
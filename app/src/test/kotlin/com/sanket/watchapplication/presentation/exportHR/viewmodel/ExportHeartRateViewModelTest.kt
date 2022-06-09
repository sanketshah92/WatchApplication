package com.sanket.watchapplication.presentation.exportHR.viewmodel


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.google.common.truth.Truth.assertThat
import com.sanket.domain.models.HeartRateData
import com.sanket.domain.usecase.GetHeartRateHistoryUseCase
import com.sanket.watchapplication.utils.mock
import com.sanket.watchapplication.utils.whenever
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class ExportHeartRateViewModelTest {
    @DelicateCoroutinesApi
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private val useCase = mock<GetHeartRateHistoryUseCase>()
    private val exportHeartRateViewModel: ExportHeartRateViewModel by lazy {
        ExportHeartRateViewModel(
            useCase
        )
    }

    @Before
    fun init() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @Test
    fun `fetch user heart rate from usecase with positive integers`() {
        val heartRateData = mutableListOf<HeartRateData>()
        heartRateData.add(
            HeartRateData(
                id = 0,
                heartRate = 72,
                timestamp = System.currentTimeMillis()
            )
        )
        runBlocking {
            launch(Dispatchers.Main) {
                whenever(useCase.execute()).thenReturn(heartRateData)
            }
        }
        exportHeartRateViewModel.fetchUserHeartRateData().observeForever(Observer {
            assertThat(it).isEqualTo(heartRateData)
        })
    }

    @Test
    fun `fetch user heart rate from usecase with null value`() {
        runBlocking {
            launch(Dispatchers.Main) {
                whenever(useCase.execute()).thenReturn(null)
            }
        }
        exportHeartRateViewModel.fetchUserHeartRateData().observeForever(Observer {
            assertThat(it).isEqualTo(null)
        })
    }

    @Test
    fun `fetch user heart rate from usecase with negative heart rate`() {
        val heartRateData = mutableListOf<HeartRateData>()
        heartRateData.add(
            HeartRateData(
                id = 0,
                heartRate = -72,
                timestamp = System.currentTimeMillis()
            )
        )
        runBlocking {
            launch(Dispatchers.Main) {
                whenever(useCase.execute()).thenReturn(heartRateData)
            }
        }
        exportHeartRateViewModel.fetchUserHeartRateData().observeForever(Observer {
            assertThat(it).isEqualTo(emptyList<HeartRateData>())
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
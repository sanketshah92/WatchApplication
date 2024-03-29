package com.sanket.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.sanket.domain.IHeartRateRepository
import com.sanket.domain.models.HeartRateData
import com.sanket.watchapplication.utils.mock
import com.sanket.watchapplication.utils.whenever
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class GetHeartRateHistoryUseCaseTest {
    @DelicateCoroutinesApi
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @get:Rule
    val rule = InstantTaskExecutorRule()
    private val repository = mock<IHeartRateRepository>()
    val useCase: GetHeartRateHistoryUseCase by lazy {
        GetHeartRateHistoryUseCase(repository)
    }

    @Before
    fun init() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @Test
    fun `fetch heart rate history data from repository`() {
        val heartRateHistoryData = mutableListOf<HeartRateData>()
        heartRateHistoryData.add(
            HeartRateData(
                id = 0,
                heartRate = 72,
                timestamp = System.currentTimeMillis()
            )
        )
        runBlocking {
            launch(Dispatchers.Main) {
                whenever(useCase.execute()).thenReturn(heartRateHistoryData)
                Truth.assertThat(useCase.execute()).isEqualTo(heartRateHistoryData)
            }
        }

    }
    @ExperimentalCoroutinesApi
    @DelicateCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }
}
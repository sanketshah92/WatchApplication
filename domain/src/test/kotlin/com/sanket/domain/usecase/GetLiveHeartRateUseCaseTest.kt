package com.sanket.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.google.common.truth.Truth.assertThat
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

class GetLiveHeartRateUseCaseTest {
    @DelicateCoroutinesApi
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val repository = mock<IHeartRateRepository>()
    val useCase: GetLiveHeartRateUseCase by lazy {
        GetLiveHeartRateUseCase(repository)
    }

    @Before
    fun init() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @Test
    fun `execute use case to get live heart rate from db`() {
        runBlocking {
            launch(Dispatchers.Main) {
                val liveHearRate = MutableLiveData<HeartRateData>()
                liveHearRate.value =
                    HeartRateData(id = 0, heartRate = 72, timestamp = System.currentTimeMillis())
                whenever(useCase.execute()).thenReturn(liveHearRate)
                assertThat(useCase.execute()).isEqualTo(liveHearRate)
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
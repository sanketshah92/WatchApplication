package com.sanket.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sanket.domain.HeartRateRepository
import com.sanket.domain.models.HeartRateData
import com.sanket.watchapplication.utils.mock
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CreateCSVUseCaseTest {
    @DelicateCoroutinesApi
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @get:Rule
    val rule = InstantTaskExecutorRule()
    private val repository = mock<HeartRateRepository>()
    val usecase: CreateCSVUseCase by lazy {
        CreateCSVUseCase(repository)
    }

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @Test
    fun `execute usecase and monitor successful CSV status`() {
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
               //To-Do need to update later.
                assert(true)
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
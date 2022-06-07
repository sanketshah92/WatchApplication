package com.sanket.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.sanket.data.datasource.HeartRateLocalDataSource
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

class HeartRateRepositoryImplTest {
    @DelicateCoroutinesApi
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @get:Rule
    val rule = InstantTaskExecutorRule()
    private val dataSource = mock<HeartRateLocalDataSource>()
    private val repository: HeartRateRepositoryImpl by lazy {
        HeartRateRepositoryImpl(dataSource)
    }

    @ExperimentalCoroutinesApi
    @Before
    fun init() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @Test
    fun `get heart rate history data from datasource`(){
        val heartRateData = mutableListOf<HeartRateData>()
        heartRateData.add(HeartRateData(id = 0, heartRate = 72, timestamp = System.currentTimeMillis()))
        runBlocking {
            launch(Dispatchers.Main){
                whenever(repository.getHeartRateHistoryData()).thenReturn(heartRateData)
                assertThat(repository.getHeartRateHistoryData()).isEqualTo(heartRateData)
            }
        }
    }


    @DelicateCoroutinesApi
    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

}
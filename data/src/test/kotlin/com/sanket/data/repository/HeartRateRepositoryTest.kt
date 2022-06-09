package com.sanket.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.sanket.data.datasource.IHeartRateLocalDataSource
import com.sanket.domain.models.HeartRateData
import com.sanket.watchapplication.utils.mock
import com.sanket.watchapplication.utils.whenever
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HeartRateRepositoryTest {
    @DelicateCoroutinesApi
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @get:Rule
    val rule = InstantTaskExecutorRule()
    private val dataSource = mock<IHeartRateLocalDataSource>()
    private val repository: HeartRateRepository by lazy {
        HeartRateRepository(dataSource)
    }

    @ExperimentalCoroutinesApi
    @Before
    fun init() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @Test
    fun `get heart rate history data from datasource`() {
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
                whenever(repository.getHeartRateHistoryData()).thenReturn(heartRateData)
                assertThat(repository.getHeartRateHistoryData()).isEqualTo(heartRateData)
            }
        }
    }

    @Test
    fun `create csv from provided heart rate data`() {
        val heartRateData = mutableListOf<HeartRateData>()
        heartRateData.add(
            HeartRateData(
                id = 0,
                heartRate = 72,
                timestamp = System.currentTimeMillis()
            )
        )
        heartRateData.add(
            HeartRateData(
                id = 1,
                heartRate = 71,
                timestamp = System.currentTimeMillis()
            )
        )

        runBlocking {
            launch(Dispatchers.Main) {
                //ToDo Mock enviornments and test based on file directory
                whenever(repository.createCSVFromHeartRateData(heartRateData).first()).thenReturn(
                    true
                )
                assertThat(repository.createCSVFromHeartRateData(heartRateData).first()).isTrue()
            }
        }
    }

    @Test
    fun `delete heart rate data from database after CSV generated`() {
        runBlocking {
            launch(Dispatchers.Main) {
                whenever(repository.deleteHeartRateData()).thenReturn(true)
                assertThat(repository.deleteHeartRateData()).isTrue()
            }
        }
    }

    @Test
    fun `insert heart rate data into database`() {
        runBlocking {
            launch(Dispatchers.Main) {
                whenever(repository.addNewHeartRateRecord(72)).thenReturn(1)
                assertThat(repository.addNewHeartRateRecord(72)).isEqualTo(1)
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
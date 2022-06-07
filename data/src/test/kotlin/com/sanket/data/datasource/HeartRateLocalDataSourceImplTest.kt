package com.sanket.data.datasource

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.sanket.data.db.HeartRateDAO
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


class HeartRateLocalDataSourceImplTest {
    @DelicateCoroutinesApi
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val dao = mock<HeartRateDAO>()
    private val dataSource: HeartRateLocalDataSourceImpl by lazy {
        HeartRateLocalDataSourceImpl(dao)
    }

    @ExperimentalCoroutinesApi
    @Before
    fun init() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @Test
    fun `fetch heart rate records with non null data`() {
        val heartRateData = mutableListOf<HeartRateData>()
        heartRateData.add(
            HeartRateData(
                id = 1,
                heartRate = 72,
                timestamp = System.currentTimeMillis()
            )
        )
        runBlocking {
            launch(Dispatchers.Main) {
                whenever(dataSource.getAllHeartRateDataFromDB()).thenReturn(heartRateData)
                assertThat(dataSource.getAllHeartRateDataFromDB()).isEqualTo(heartRateData)
            }
        }
    }

    @Test
    fun `add heart rate record into database`(){
        val heartRateData = HeartRateData(id = 0, heartRate = 80, timestamp = System.currentTimeMillis())
        runBlocking {
            launch(Dispatchers.Main) {
                whenever(dataSource.addHeartRateDataToDB(heartRateData)).thenReturn(101)
                assertThat(dataSource.addHeartRateDataToDB(heartRateData)).isEqualTo(101)
            }
        }
    }

    @Test
    fun `delete heart rate data from databse`(){
        runBlocking {
            launch(Dispatchers.Main){
                whenever(dataSource.removeAllHeartRateDataFromDB()).thenReturn(true)
                assertThat(dataSource.removeAllHeartRateDataFromDB()).isTrue()
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
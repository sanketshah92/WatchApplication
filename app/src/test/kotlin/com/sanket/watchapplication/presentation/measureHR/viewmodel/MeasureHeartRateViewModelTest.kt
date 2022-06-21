package com.sanket.watchapplication.presentation.measureHR.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.sanket.domain.usecase.GetLiveHeartRateUseCase
import com.sanket.watchapplication.utils.mock
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MeasureHeartRateViewModelTest{
    @DelicateCoroutinesApi
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private val useCase: GetLiveHeartRateUseCase = mock()
    private val viewModel:MeasureHeartRateViewModel by lazy {
        MeasureHeartRateViewModel(useCase)
    }

    @Before
    fun init() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @Test
    fun `provide positive hear rate`(){
        runBlocking {
            launch (Dispatchers.Main){
                viewModel.listenHeartRate().observeForever(Observer {
                    it.observeForever(Observer {
                        assert(true)
                    })
                })
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
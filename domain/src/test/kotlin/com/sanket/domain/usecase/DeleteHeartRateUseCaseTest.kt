package com.sanket.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.sanket.domain.IHeartRateRepository
import com.sanket.watchapplication.utils.mock
import com.sanket.watchapplication.utils.whenever
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DeleteHeartRateUseCaseTest{
    @DelicateCoroutinesApi
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @get:Rule
    val rule = InstantTaskExecutorRule()
    private val repository = mock<IHeartRateRepository>()
    val useCase:DeleteHeartRateUseCase by lazy {
        DeleteHeartRateUseCase(repository)
    }
    @Before
    fun init() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @Test
    fun `execute usecase and observe data deletion`(){
        runBlocking {
            launch(Dispatchers.Main){
                whenever(useCase.execute()).thenReturn(true)
                assertThat(useCase.execute()).isTrue()
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
package com.sanket.watchapplication.presentation.di

import com.sanket.watchapplication.presentation.exportHR.viewmodel.ExportHeartRateViewModel
import com.sanket.watchapplication.presentation.exportHR.viewmodel.HeartRateToCSVViewModel
import com.sanket.watchapplication.presentation.measureHR.viewmodel.MeasureHeartRateViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { ExportHeartRateViewModel(get()) }
    viewModel { HeartRateToCSVViewModel(get(), get()) }
    viewModel { MeasureHeartRateViewModel(get()) }
}
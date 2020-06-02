package br.com.desafio_android_jonathan_feitosa.di

import br.com.desafio_android_jonathan_feitosa.ui.home.HomeViewModel
import br.com.desafio_android_jonathan_feitosa.ui.hq.HqViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel( get()) }
    viewModel { HqViewModel( get()) }

}
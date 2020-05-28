package br.com.desafio_android_jonathan_feitosa.di

import br.com.desafio_android_jonathan_feitosa.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel( get()) }
}
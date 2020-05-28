package br.com.desafio_android_jonathan_feitosa.di

import br.com.desafio_android_jonathan_feitosa.repository.UpcomingHeroesRepository
import br.com.desafio_android_jonathan_feitosa.repository.UpcomingHeroesRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<UpcomingHeroesRepository> { UpcomingHeroesRepositoryImpl(get()) }
}
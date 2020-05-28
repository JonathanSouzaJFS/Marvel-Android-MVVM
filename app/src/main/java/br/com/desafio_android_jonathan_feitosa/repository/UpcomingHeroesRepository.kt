package br.com.desafio_android_jonathan_feitosa.repository

import br.com.desafio_android_jonathan_feitosa.models.Hero


interface UpcomingHeroesRepository {
    suspend fun getUpcomingList(offset: Int): Pair< List<Hero>?, Int>
}

class UpcomingHeroesRepositoryImpl(private val service: IServiceRetrofit): UpcomingHeroesRepository{

    override suspend fun getUpcomingList(offset:Int): Pair< List<Hero>?, Int> {
        val result = service.getAllEvents(offset)
        return Pair(result.data.results, result.data.total)
    }
}
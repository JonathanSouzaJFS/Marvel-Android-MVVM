package br.com.desafio_android_jonathan_feitosa.repository

import br.com.desafio_android_jonathan_feitosa.models.ComicsResponse


interface ComicsRepository {
    suspend fun getComicsId(comicId: String): Pair<List<ComicsResponse.Data.Result>, String>
}

class ComicsRepositoryImpl(private val service: IServiceRetrofit): ComicsRepository{

    override suspend fun getComicsId(comicId:String): Pair<List<ComicsResponse.Data.Result>, String> {
        val result = service.getComicsId(comicId)

        return Pair(result.data.results, result.data.total)
    }
}
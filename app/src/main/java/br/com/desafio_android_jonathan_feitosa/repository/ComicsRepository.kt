package br.com.desafio_android_jonathan_feitosa.repository

import android.util.Log
import br.com.desafio_android_jonathan_feitosa.models.ComicsId


interface ComicsRepository {
    suspend fun getComicsId(comicId: Int): Pair< List<ComicsId>?, Int>
}

class ComicsRepositoryImpl(private val service: IServiceRetrofit): ComicsRepository{

    override suspend fun getComicsId(comicId:Int): Pair< List<ComicsId>?, Int> {
        val result = service.getComicsId(comicId)

        Log.i("ResultadoJFS", result.toString());
        Log.i("ResultadoJFS", result.results.toString());

        return Pair(result.results, result.total)
    }
}
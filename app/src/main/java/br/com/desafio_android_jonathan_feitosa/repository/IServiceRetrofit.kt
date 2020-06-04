package br.com.desafio_android_jonathan_feitosa.repository


import br.com.desafio_android_jonathan_feitosa.models.CharacterReturn
import br.com.desafio_android_jonathan_feitosa.models.ComicsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IServiceRetrofit {

    @GET("characters")
    suspend fun getAllEvents(
        @Query("offset") offset:Int) : CharacterReturn

    @GET("characters/{characterId}/comics")
    suspend fun getComicsId(
        @Path("characterId") id: String) : ComicsResponse
}
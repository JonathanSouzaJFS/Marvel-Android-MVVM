package br.com.desafio_android_jonathan_feitosa.repository


import br.com.desafio_android_jonathan_feitosa.models.CharacterReturn
import retrofit2.http.GET
import retrofit2.http.Query

interface IServiceRetrofit {

    @GET("characters")
    suspend fun getAllEvents(
  //      @Query("limit") limit:Int,
        @Query("offset") offset:Int) : CharacterReturn
//        @Query("ts") ts:Long,
//        @Query("apikey") apiKey: String,
//        @Query("hash") hash: String): CharacterReturn
}
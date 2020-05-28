package br.com.desafio_android_jonathan_feitosa.di

import br.com.desafio_android_jonathan_feitosa.BuildConfig
import br.com.desafio_android_jonathan_feitosa.repository.IServiceRetrofit
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.concurrent.TimeUnit

val networkModule = module {

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(createOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        get<Retrofit>().create<IServiceRetrofit>()
    }
}

fun createOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor { chain -> addRequestParameters(chain) }
        .build()
}

fun addRequestParameters(chain: Interceptor.Chain): Response {
    var request = chain.request()
    val builder = request.url().newBuilder()

    builder
        .addQueryParameter("limit", BuildConfig.CHARACTER_LIMIT.toString())
        .addQueryParameter("hash", getHash())
        .addQueryParameter("ts", getTs().toString())
        .addQueryParameter("apikey", BuildConfig.API_PUBLIC_KEY)

    return chain.proceed(request.newBuilder().url(builder.build()).build())
}

fun getHash(): String {
    try {
        val md = MessageDigest.getInstance("MD5")
        val messageDigest = md.digest(getTs().toString().toByteArray()
                + BuildConfig.API_PRIVATE_KEY.toByteArray()
                + BuildConfig.API_PUBLIC_KEY.toByteArray())

        var hashtext = BigInteger(1, messageDigest).toString(16)
        while (hashtext.length < 32) {
            hashtext = "0$hashtext"
        }
        return hashtext
    } catch (e: NoSuchAlgorithmException) {
        throw RuntimeException(e)
    }
}

fun getTs() : Long{
    return 1L
}
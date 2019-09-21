package com.github.mosamman.opencvgradle.dependencies

import com.github.mosamman.opencvgradle.data.repository.GithubURL
import com.github.mosamman.opencvgradle.data.repository.RepositoryAccess
import com.github.mosamman.opencvgradle.data.repository.RepositoryApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RepositoryModule {
    val get = module {
        single(override = true) { PluginLogger() }
        single(override = true) { httpLoggingInterceptor(get()) }
        single(override = true) { okHttpClient(get()) }
        single(override = true) { gsonFactory() }
        single(override = true) { getWebserviceInstance(get(), get()) }

        single(override = true) { get<Retrofit>().create(RepositoryApi::class.java) }

        single(override = true) { RepositoryAccess(get()) }
    }


    private fun getWebserviceInstance(
            okHttpClient: OkHttpClient,
            gsonFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder()
            .baseUrl(GithubURL.GITHUB_API)
            .client(okHttpClient)
            .addConverterFactory(gsonFactory)
            .build()

    private const val creationDateConverter: Long = 10
    private const val readTimeOutConverter: Long = 100

    private fun okHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val connectionTimeOut: Long = creationDateConverter
        val readTimeOut: Long = readTimeOutConverter
        return OkHttpClient().newBuilder()
                .connectTimeout(connectionTimeOut, TimeUnit.SECONDS)
                .readTimeout(readTimeOut, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .build()
    }


    private fun httpLoggingInterceptor(pluginLogger: PluginLogger): HttpLoggingInterceptor {
        val logger = HttpLoggingInterceptor(pluginLogger)
        logger.level = HttpLoggingInterceptor.Level.BASIC
        return logger
    }


    private fun gsonFactory() = GsonConverterFactory.create()


    class PluginLogger : HttpLoggingInterceptor.Logger {
        override fun log(message: String) {
            println(message)
        }

    }

}
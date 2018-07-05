package com.example.jacpalberto.pokemonexample.di.modules

import com.example.jacpalberto.pokemonexample.BuildConfig
import com.example.jacpalberto.pokemonexample.data.PokemonService
import com.example.jacpalberto.pokemonexample.repositories.PokemonRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Alberto Carrillo on 7/5/18.
 */
@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun getPokemonRepository(pokemonService: PokemonService): PokemonRepository {
        return PokemonRepository(pokemonService)
    }

    @Provides
    @Singleton
    fun getRestClient(converterFactory: Converter.Factory, okHttpClient: OkHttpClient): Retrofit =
            Retrofit.Builder()
                    .baseUrl(BuildConfig.API_URL)
                    .client(okHttpClient)
                    .addConverterFactory(converterFactory)
                    .build()

    @Provides
    @Singleton
    fun getGithubService(restClient: Retrofit): PokemonService =
            restClient.create(PokemonService::class.java)

    @Provides
    @Singleton
    fun provideGsonParserFactory(): Converter.Factory =
            GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }
}
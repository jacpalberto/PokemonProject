package com.example.jacpalberto.pokemonexample.data

import com.example.jacpalberto.pokemonexample.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Alberto Carrillo on 6/1/18.
 */
object BaseClient {
    private fun provideClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    private fun retrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .client(provideClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    fun provideApiService() = retrofit().create(Api::class.java)
}
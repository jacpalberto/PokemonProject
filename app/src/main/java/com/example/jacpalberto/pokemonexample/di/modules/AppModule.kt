package com.example.jacpalberto.pokemonexample.di.modules

import android.content.Context
import com.example.jacpalberto.pokemonexample.app.PokemonApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Alberto Carrillo on 7/5/18.
 */
@Module
class AppModule
{
    @Provides
    @Singleton
    fun provideApplication(app : PokemonApp):Context = app
}
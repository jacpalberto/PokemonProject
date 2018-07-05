package com.example.jacpalberto.pokemonexample.di

import com.example.jacpalberto.pokemonexample.app.PokemonApp
import com.example.jacpalberto.pokemonexample.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by Alberto Carrillo on 7/5/18.
 */
@Singleton
@Component(modules = [AppModule::class, AndroidInjectionModule::class, ActivityBuilder::class,
    ViewModelModule::class, RepositoryModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: PokemonApp): Builder

        fun build(): AppComponent
    }

    fun inject(app: PokemonApp)
}
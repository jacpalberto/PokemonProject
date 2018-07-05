package com.example.jacpalberto.pokemonexample.di.modules

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.jacpalberto.pokemonexample.di.ViewModelKey
import com.example.jacpalberto.pokemonexample.pokemon_detail.PokemonDetailViewModel
import com.example.jacpalberto.pokemonexample.pokemon_list.PokemonListViewModel
import com.example.jacpalberto.pokemonexample.utils.FactoryViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Alberto Carrillo on 7/5/18.
 */
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(PokemonListViewModel::class)
    internal abstract fun bindPokemonViewModel(pokemonListViewModel: PokemonListViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: FactoryViewModel): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(PokemonDetailViewModel::class)
    internal abstract fun bindPokemonDetailViewModel(pokemonDetailViewModel: PokemonDetailViewModel): ViewModel


}
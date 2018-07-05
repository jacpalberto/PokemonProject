package com.example.jacpalberto.pokemonexample.di.modules

import com.example.jacpalberto.pokemonexample.pokemon_detail.PokemonDetailActivity
import com.example.jacpalberto.pokemonexample.pokemon_list.PokemonListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Alberto Carrillo on 7/5/18.
 */
@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun bindPokemonListActivity(): PokemonListActivity

    @ContributesAndroidInjector
    abstract fun bindPokemonDetailActivity(): PokemonDetailActivity
}
package com.example.jacpalberto.pokemonexample.activities.pokemon_list

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.example.jacpalberto.pokemonexample.models.Pokemon
import com.example.jacpalberto.pokemonexample.repositories.PokemonListRepository

/**
 * Created by Alberto Carrillo on 6/21/18.
 */

class PokemonListViewModel : ViewModel() {
    private var pokemonList: MutableLiveData<List<Pokemon>>? = null
    private var toastMessage: MutableLiveData<String>? = null

    fun observeMessages(): LiveData<String> {
        if (toastMessage == null) {
            toastMessage = MutableLiveData()
        }
        return toastMessage as LiveData<String>
    }

    fun getPokemons(): MutableLiveData<List<Pokemon>> {
        if (pokemonList == null) {
            pokemonList = MutableLiveData()
            pokemonList = PokemonListRepository.getPokemons()
        }
        return pokemonList as MutableLiveData<List<Pokemon>>
    }

  /*  val getPagingPokemons =
            LivePagedListBuilder(PokemonListRepository.getPokemons(), PagedList.Config.Builder()
            .setPageSize(20)
            .setEnablePlaceholders(true)
            .build()).build()*/
}
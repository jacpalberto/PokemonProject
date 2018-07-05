package com.example.jacpalberto.pokemonexample.pokemon_list

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.jacpalberto.pokemonexample.models.Pokemon
import com.example.jacpalberto.pokemonexample.repositories.PokemonRepository
import javax.inject.Inject

/**
 * Created by Alberto Carrillo on 6/21/18.
 */

class PokemonListViewModel @Inject constructor(private val repository: PokemonRepository) : ViewModel() {
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
            repository.getPokemons(::updateErrorMessage) {
                pokemonList?.value = it.value
            }
        }
        return pokemonList as MutableLiveData<List<Pokemon>>
    }

    private fun updateErrorMessage() {
        toastMessage?.value = "Could not update pokemons"
    }
}
package com.example.jacpalberto.pokemonexample.pokemon_detail

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.jacpalberto.pokemonexample.models.PokemonDetail
import com.example.jacpalberto.pokemonexample.repositories.PokemonRepository
import javax.inject.Inject

/**
 * Created by Alberto Carrillo on 6/25/18.
 */
class PokemonDetailViewModel @Inject constructor(private val repository: PokemonRepository) : ViewModel() {
    private var pokemonDetail: MutableLiveData<PokemonDetail>? = null

    fun getPokemonData(id: Int?): MutableLiveData<PokemonDetail> {
        if (pokemonDetail == null) {
            pokemonDetail = MutableLiveData()

            repository.getPokemonDetail(id ?: 0, ::updateErrorMessage) {
                pokemonDetail?.value = it.value
            }
        }
        return pokemonDetail as MutableLiveData<PokemonDetail>
    }

    private fun updateErrorMessage() {
    }
}
package com.example.jacpalberto.pokemonexample.activities.pokemon_detail

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.jacpalberto.pokemonexample.models.PokemonDetail
import com.example.jacpalberto.pokemonexample.repositories.PokemonDetailRepository

/**
 * Created by Alberto Carrillo on 6/25/18.
 */
class PokemonDetailViewModel : ViewModel() {
    private var pokemonDetail: MutableLiveData<PokemonDetail>? = null

    fun getPokemonData(id: Int?): MutableLiveData<PokemonDetail> {
        if (pokemonDetail == null) {
            pokemonDetail = MutableLiveData()
            pokemonDetail = PokemonDetailRepository.getPokemonDetail(id ?: 0)
        }
        return pokemonDetail as MutableLiveData<PokemonDetail>
    }
}
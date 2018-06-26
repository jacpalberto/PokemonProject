package com.example.jacpalberto.pokemonexample.repositories

import android.arch.lifecycle.MutableLiveData
import com.example.jacpalberto.pokemonexample.data.BaseClient
import com.example.jacpalberto.pokemonexample.models.Pokemon
import com.example.jacpalberto.pokemonexample.models.PokemonResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Alberto Carrillo on 6/22/18.
 */
object PokemonListRepository {

    fun getPokemons(): MutableLiveData<List<Pokemon>> {
        val data = MutableLiveData<List<Pokemon>>()
        val call = BaseClient.provideApiService().getPokemonList(160)
        call.enqueue(object : Callback<PokemonResponse> {
            override fun onResponse(call: Call<PokemonResponse>, response: Response<PokemonResponse>) {
                if (response.isSuccessful) {
                    val pokemonResponse = response.body()
                    if (pokemonResponse?.results != null) {
                        data.value = pokemonResponse.results
                    }
                }
            }

            override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
            }
        })
        return data
    }
}
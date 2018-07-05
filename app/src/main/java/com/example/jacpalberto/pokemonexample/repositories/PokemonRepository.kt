package com.example.jacpalberto.pokemonexample.repositories

import android.arch.lifecycle.MutableLiveData
import com.example.jacpalberto.pokemonexample.data.PokemonService
import com.example.jacpalberto.pokemonexample.models.Pokemon
import com.example.jacpalberto.pokemonexample.models.PokemonDetail
import com.example.jacpalberto.pokemonexample.models.PokemonResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Alberto Carrillo on 7/5/18.
 */
@Singleton
class PokemonRepository @Inject constructor(private val pokemonService: PokemonService) {

    fun getPokemons(onFailure: () -> Unit, onSuccess: (MutableLiveData<List<Pokemon>>) -> Unit) {
        val data = MutableLiveData<List<Pokemon>>()
        val call = pokemonService.getPokemonList(160)
        call.enqueue(object : Callback<PokemonResponse> {
            override fun onResponse(call: Call<PokemonResponse>, response: Response<PokemonResponse>) {
                if (response.isSuccessful) {
                    val pokemonResponse = response.body()
                    if (pokemonResponse?.results != null) {
                        data.value = pokemonResponse.results
                        onSuccess(data)
                    }
                } else onFailure()
            }

            override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                onFailure()
            }
        })
    }

    fun getPokemonDetail(id: Int, onFailure: () -> Unit, onSuccess: (MutableLiveData<PokemonDetail>) -> Unit) {
        val data = MutableLiveData<PokemonDetail>()
        val call = pokemonService.getPokemonDetail(id)
        call.enqueue(object : Callback<PokemonDetail> {
            override fun onResponse(call: Call<PokemonDetail>, response: Response<PokemonDetail>) {
                if (response.isSuccessful) {
                    val pokemonResponse = response.body()
                    if (pokemonResponse != null) {
                        data.value = pokemonResponse
                        onSuccess(data)
                    }
                }
                onFailure()
            }

            override fun onFailure(call: Call<PokemonDetail>, t: Throwable) {
                onFailure()
            }
        })
    }
}
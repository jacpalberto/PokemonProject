package com.example.jacpalberto.pokemonexample.repositories

import android.arch.lifecycle.MutableLiveData
import com.example.jacpalberto.pokemonexample.data.BaseClient
import com.example.jacpalberto.pokemonexample.models.PokemonDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Alberto Carrillo on 6/24/18.
 */
object PokemonDetailRepository {
    fun getPokemonDetail(id: Int): MutableLiveData<PokemonDetail> {
        val data = MutableLiveData<PokemonDetail>()
        val call = BaseClient.provideApiService().getPokemonDetail(id)
        call.enqueue(object : Callback<PokemonDetail> {
            override fun onResponse(call: Call<PokemonDetail>, response: Response<PokemonDetail>) {
                if (response.isSuccessful) {
                    val pokemonResponse = response.body()
                    if (pokemonResponse != null) {
                        data.value = pokemonResponse
                    }
                }
            }

            override fun onFailure(call: Call<PokemonDetail>, t: Throwable) {
            }
        })
        return data
    }
}
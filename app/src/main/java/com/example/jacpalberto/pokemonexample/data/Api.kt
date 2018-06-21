package com.example.jacpalberto.pokemonexample.data

import com.example.jacpalberto.pokemonexample.models.PokemonResponse
import retrofit2.Call
import retrofit2.http.GET


/**
 * Created by Alberto Carrillo on 6/1/18.
 */
interface Api {
    @GET("pokemon")
    fun getPokemonList(): Call<PokemonResponse>
}
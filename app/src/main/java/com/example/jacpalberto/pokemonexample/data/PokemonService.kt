package com.example.jacpalberto.pokemonexample.data

import com.example.jacpalberto.pokemonexample.models.PokemonDetail
import com.example.jacpalberto.pokemonexample.models.PokemonResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Alberto Carrillo on 6/1/18.
 */
interface PokemonService {
    @GET("pokemon/")
    fun getPokemonList(@Query("limit") limit: Int): Call<PokemonResponse>

    @GET("pokemon/{userId}")
    fun getPokemonDetail(@Path("userId") id: Int): Call<PokemonDetail>
}
package com.example.jacpalberto.pokemonexample.models

import android.util.Log
import com.google.gson.annotations.SerializedName

/**
 * Created by Alberto Carrillo on 6/1/18.
 */
data class Pokemon(val name: String?, val url: String?) {
    val id: Int
        get () {
            if (url == null) return 0
            else {
                val urlSplitted = url.split("/")
                return urlSplitted[urlSplitted.size - 2].toIntOrNull() ?: 0
            }
        }
}

data class PokemonResponse(
        @SerializedName("results") val results: MutableList<Pokemon>?)
package com.example.jacpalberto.pokemonexample.models

import com.google.gson.annotations.SerializedName

/**
 * Created by Alberto Carrillo on 6/1/18.
 */
data class Pokemon(val name: String?, val url: String?) {
    val id: Int
        get () {
            return if (url == null) 0
            else {
                val urlSplitted = url.split("/")
                urlSplitted[urlSplitted.size - 2].toIntOrNull() ?: 0
            }
        }
}

data class PokemonDetail(@SerializedName("stats") val stats: List<Stat>? = null,
                         @SerializedName("name") val name: String? = null,
                         @SerializedName("weight") val weight: Int? = null,
                         @SerializedName("id") val id: Int? = null,
                         @SerializedName("base_experience") val baseExperience: Int? = null,
                         @SerializedName("types") val types: List<Type>? = null)

data class Stat(@SerializedName("stat") val stat: StatData? = null,
                @SerializedName("effort") val effort: Int? = null,
                @SerializedName("base_stat") val baseStat: Int? = null)

data class StatData(@SerializedName("url") val url: String? = null,
                    @SerializedName("name") val name: String? = null)

data class Type(@SerializedName("slot") val slot: Int? = null,
                @SerializedName("type") val type: TypeData? = null)

data class TypeData(@SerializedName("url") val url: String? = null,
                    @SerializedName("name") val name: String? = null)

data class PokemonResponse(@SerializedName("results")
                           val results: MutableList<Pokemon>?)
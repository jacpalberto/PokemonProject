package com.example.jacpalberto.pokemonexample.activities.pokemon_list

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.example.jacpalberto.pokemonexample.R
import com.example.jacpalberto.pokemonexample.models.Pokemon

/**
 * Created by Alberto Carrillo on 6/26/18.
 */
class PokemonPagingAdapter() : PagedListAdapter<Pokemon, PokemonPagingAdapter.PokemonViewHolder>(
        object : DiffUtil.ItemCallback<Pokemon>() {
            override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon) = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon) =
                    oldItem.name == newItem.name && oldItem.id == newItem.id
        }
) {
    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder =
            PokemonViewHolder(parent)

    class PokemonViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)) {

        private val pokemonName = itemView.findViewById<TextView>(R.id.pokemonName)

        fun bindTo(pokemon: Pokemon?) {
            pokemonName.text = pokemon?.name
        }
    }
}
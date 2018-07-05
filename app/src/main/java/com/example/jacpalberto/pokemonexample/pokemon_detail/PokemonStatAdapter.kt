package com.example.jacpalberto.pokemonexample.pokemon_detail

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jacpalberto.pokemonexample.R
import com.example.jacpalberto.pokemonexample.models.Stat
import kotlinx.android.synthetic.main.item_pokemon_stat.view.*

/**
 * Created by Alberto Carrillo on 6/25/18.
 */
class PokemonStatAdapter(private val dataset: List<Stat>) : RecyclerView.Adapter<PokemonStatAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon_stat, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount() = dataset.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataset[position], position)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(stat: Stat, position: Int) = with(itemView) {
            pokemonStatNameTextView.text = stat.stat?.name?.trim()
            pokemonStatValueTextView.text = stat.baseStat.toString()
            if (position % 2 == 0) pokemonStatItemLayout.setBackgroundResource(R.color.emerald)
            else pokemonStatItemLayout.setBackgroundResource(R.color.emeraldDark)
        }
    }
}
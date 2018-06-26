package com.example.jacpalberto.pokemonexample.activities.pokemon_detail

import android.graphics.drawable.GradientDrawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jacpalberto.pokemonexample.R
import com.example.jacpalberto.pokemonexample.models.Type
import kotlinx.android.synthetic.main.item_pokemon_type.view.*


/**
 * Created by Alberto Carrillo on 6/1/18.
 */

class PokemonTypeAdapter(private var dataset: List<Type>) : RecyclerView.Adapter<PokemonTypeAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataset[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon_type, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount() = dataset.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(type: Type) = with(itemView) {
            if (type.type != null) {
                pokemonTypeTextView.text = type.type.name?.toUpperCase()
                when (type.type.name) {
                    "normal" -> setItemColors(itemView,
                            backgroundColor = context.getColor(R.color.normal),
                            strokeColor = context.getColor(R.color.normalDark))
                    "fighting" -> setItemColors(itemView,
                            backgroundColor = context.getColor(R.color.fighting),
                            strokeColor = context.getColor(R.color.fightingDark))
                    "flying" -> setItemColors(itemView,
                            backgroundColor = context.getColor(R.color.flying),
                            strokeColor = context.getColor(R.color.flyingDark))
                    "poison" -> setItemColors(itemView,
                            backgroundColor = context.getColor(R.color.poison),
                            strokeColor = context.getColor(R.color.poisonDark))
                    "ground" -> setItemColors(itemView,
                            backgroundColor = context.getColor(R.color.ground),
                            strokeColor = context.getColor(R.color.groundDark))
                    "rock" -> setItemColors(itemView,
                            backgroundColor = context.getColor(R.color.rock),
                            strokeColor = context.getColor(R.color.rockDark))
                    "bug" -> setItemColors(itemView,
                            backgroundColor = context.getColor(R.color.bug),
                            strokeColor = context.getColor(R.color.bugDark))
                    "ghost" -> setItemColors(itemView,
                            backgroundColor = context.getColor(R.color.ghost),
                            strokeColor = context.getColor(R.color.ghostDark))
                    "steel" -> setItemColors(itemView,
                            backgroundColor = context.getColor(R.color.steel),
                            strokeColor = context.getColor(R.color.steelDark))
                    "fire" -> setItemColors(itemView,
                            backgroundColor = context.getColor(R.color.fire),
                            strokeColor = context.getColor(R.color.fireDark))
                    "water" -> setItemColors(itemView,
                            backgroundColor = context.getColor(R.color.water),
                            strokeColor = context.getColor(R.color.waterDark))
                    "grass" -> setItemColors(itemView,
                            backgroundColor = context.getColor(R.color.grass),
                            strokeColor = context.getColor(R.color.grassDark))
                    "electric" -> setItemColors(itemView,
                            backgroundColor = context.getColor(R.color.electric),
                            strokeColor = context.getColor(R.color.electricDark))
                    "psychic" -> setItemColors(itemView,
                            backgroundColor = context.getColor(R.color.psychic),
                            strokeColor = context.getColor(R.color.psychicDark))
                    "ice" -> setItemColors(itemView,
                            backgroundColor = context.getColor(R.color.ice),
                            strokeColor = context.getColor(R.color.iceDark))
                    "dragon" -> setItemColors(itemView,
                            backgroundColor = context.getColor(R.color.dragon),
                            strokeColor = context.getColor(R.color.dragonDark))
                    "dark" -> setItemColors(itemView,
                            backgroundColor = context.getColor(R.color.dark),
                            strokeColor = context.getColor(R.color.darkDark))
                    "fairy" -> setItemColors(itemView,
                            backgroundColor = context.getColor(R.color.fairy),
                            strokeColor = context.getColor(R.color.fairyDark))
                }
            }
        }

        private fun setItemColors(view: View, backgroundColor: Int, strokeColor: Int) {
            val drawable = view.background as GradientDrawable
            drawable.setStroke(4, strokeColor)
            drawable.setColor(backgroundColor)
        }
    }
}
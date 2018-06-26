package com.example.jacpalberto.pokemonexample.activities.pokemon_list

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.support.v4.content.ContextCompat
import android.support.v7.graphics.Palette
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.jacpalberto.pokemonexample.R
import com.example.jacpalberto.pokemonexample.models.Pokemon
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_pokemon.view.*
import java.lang.Exception

/**
 * Created by Alberto Carrillo on 6/1/18.
 */

class PokemonListAdapter(private var dataset: List<Pokemon>, private val onPokemonClick: (Pokemon, View) -> Unit)
    : RecyclerView.Adapter<PokemonListAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataset[position], onPokemonClick)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount() = dataset.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(pokemon: Pokemon, onPokemonClick: (Pokemon, View) -> Unit) = with(itemView) {
            val url = getPokemonImageUrl(pokemon.id)
            pokemonName.text = pokemon.name

            Picasso.get()
                    .load(url)
                    .resize(300, 300)
                    .centerCrop()
                    .into(pokemonImage, object : com.squareup.picasso.Callback {
                        override fun onError(e: Exception?) {}

                        override fun onSuccess() {
                            setPaletteColors(context, pokemonImage, pokemonCardView)
                        }
                    })
            pokemonCardView.setOnClickListener { onPokemonClick(pokemon, this) }
        }

        private fun getPokemonImageUrl(id: Int?) = "http://pokeapi.co/media/sprites/pokemon/$id.png"

        private fun setPaletteColors(context: Context, pokemonImage: ImageView, pokemonCardView: CardView) {
            if (pokemonImage.drawable == null) return

            val default = ContextCompat.getColor(context, android.R.color.black)
            val drawable = pokemonImage.drawable as BitmapDrawable
            val bitmap2 = drawable.bitmap
            Palette.from(bitmap2).generate { palette ->
                pokemonCardView.setCardBackgroundColor(palette.getLightVibrantColor(default))
            }
        }
    }
}
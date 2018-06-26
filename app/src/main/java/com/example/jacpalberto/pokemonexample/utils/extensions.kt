package com.example.jacpalberto.pokemonexample.utils

import android.content.Context
import android.support.annotation.ColorRes
import android.support.v4.content.ContextCompat
import com.example.jacpalberto.pokemonexample.R

/**
 * Created by Alberto Carrillo on 6/25/18.
 */
fun Context.getcolor(@ColorRes color: Int): Int {
    return ContextCompat.getColor(this, R.color.fire)
}
package com.example.jacpalberto.pokemonexample.di

import android.arch.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

/**
 * Created by Alberto Carrillo on 7/5/18.
 */
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)
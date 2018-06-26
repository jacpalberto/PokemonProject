package com.example.jacpalberto.pokemonexample.utils

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.util.Log

/**
 * Created by Alberto Carrillo on 6/21/18.
 */
class PokemonActivityObserver : LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun createListener() {
        Log.d("Observer", "onCreate")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun startListener() {
        Log.d("Observer", "onStart")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun connectListener() {
        Log.d("Observer", "onResume")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun disconnectListener() {
        Log.d("Observer", "onPause")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun pauseListener() {
        Log.d("Observer", "onStop")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun destroyListener() {
        Log.d("Observer", "onDestroy")
    }
}
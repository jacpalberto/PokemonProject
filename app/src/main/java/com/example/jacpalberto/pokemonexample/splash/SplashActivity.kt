package com.example.jacpalberto.pokemonexample.splash

import android.animation.Animator
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView
import com.example.jacpalberto.pokemonexample.R
import com.example.jacpalberto.pokemonexample.pokemon_list.PokemonListActivity

/**
 * Created by Alberto Carrillo on 6/24/18.
 */

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        init()
    }

    private fun init() {
        val pokemonLogo = findViewById<ImageView>(R.id.pokemonLogoImageView)
        pokemonLogo.animate()
                .setDuration(1500L)
                .setInterpolator(AccelerateDecelerateInterpolator())
                .alpha(1F)
                .setListener(object : Animator.AnimatorListener {
                    override fun onAnimationRepeat(p0: Animator?) {

                    }

                    override fun onAnimationCancel(p0: Animator?) {

                    }

                    override fun onAnimationStart(p0: Animator?) {
                    }

                    override fun onAnimationEnd(p0: Animator?) {
                        startActivity(PokemonListActivity.newIntent(this@SplashActivity))
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                    }
                })
    }
}
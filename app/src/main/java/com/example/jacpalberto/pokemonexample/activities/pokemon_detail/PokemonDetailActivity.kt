package com.example.jacpalberto.pokemonexample.activities.pokemon_detail

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.graphics.Palette
import android.support.v7.widget.LinearLayoutManager
import android.transition.Fade
import android.view.View
import android.widget.ImageView
import com.example.jacpalberto.pokemonexample.R
import com.example.jacpalberto.pokemonexample.models.Pokemon
import com.example.jacpalberto.pokemonexample.models.PokemonDetail
import com.example.jacpalberto.pokemonexample.utils.AppBarStateChangeListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_pokemon_detail.*
import kotlinx.android.synthetic.main.pokemon_detail_content.*
import java.lang.Exception

/**
 * Created by Alberto Carrillo on 6/24/18.
 */

class PokemonDetailActivity : AppCompatActivity() {
    private var pokemonTypeAdapter = PokemonTypeAdapter(emptyList())
    private var pokemonStatAdapter = PokemonStatAdapter(emptyList())
    private var pokemonSelected: Pokemon? = null
    private var viewModel: PokemonDetailViewModel? = null
    private var toolbarState: String = "EXPANDED"

    companion object {
        fun newIntent(context: Context, pokemon: Pokemon) =
                Intent(context, PokemonDetailActivity::class.java).apply {
                    putExtra("url", pokemon.url)
                    putExtra("name", pokemon.name)
                }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_detail)
        viewModel = ViewModelProviders.of(this).get(PokemonDetailViewModel::class.java)
        supportPostponeEnterTransition()
        init()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (toolbarState != "EXPANDED") {
            window.sharedElementReturnTransition = null
            window.sharedElementReenterTransition = null
            toolbarPokemonImage.transitionName = null
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun init() {
        configSharedAnim()
        extractData()
        initToolbar()
        initTypeRecycler()
        initStatRecycler()
        loadPokemonImage()
        pokemonName.text = pokemonSelected?.name
        appBarLayout.addOnOffsetChangedListener(object : AppBarStateChangeListener() {
            override fun onStateChanged(appBarLayout: AppBarLayout, state: State) {
                toolbarState = state.name
                toolbarPokemonImage.visibility = if (state.name == "COLLAPSED") View.GONE else View.VISIBLE
            }
        })
        initObservers()
    }

    private fun initStatRecycler() {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)
        statsRecyclerView.layoutManager = layoutManager
    }

    private fun initTypeRecycler() {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        typeRecyclerView.layoutManager = layoutManager
    }

    private fun initObservers() {
        val id = pokemonSelected?.id
        viewModel?.getPokemonData(id)?.observe(this, Observer { showPokemonsData(it) })
    }

    private fun showPokemonsData(pokemonDetail: PokemonDetail?) {
        with(pokemonDetail) {
            this?.let {
                pokemonId.text = getString(R.string.pokemon_id, id ?: 0)
                pokemonWeight.text = getString(R.string.pokemon_weight, weight ?: 0)
                pokemonBaseExperience.text = getString(R.string.pokemon_experience, baseExperience
                        ?: 0)
                types?.let { types ->
                    pokemonTypeAdapter = PokemonTypeAdapter(types)
                    typeRecyclerView.adapter = pokemonTypeAdapter
                }
                stats?.let { stats ->
                    pokemonStatsTextView.visibility = View.VISIBLE
                    pokemonStatAdapter = PokemonStatAdapter(stats)
                    statsRecyclerView.adapter = pokemonStatAdapter
                }
            }
        }
    }

    private fun configSharedAnim() {
        val fade = Fade()
        fade.excludeTarget(R.id.toolbar, true)
        fade.excludeTarget(android.R.id.statusBarBackground, true)
        fade.excludeTarget(android.R.id.navigationBarBackground, true)
        window.enterTransition = fade
        window.exitTransition = fade
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = null
            elevation = 7.0f
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    private fun extractData() {
        if (intent == null) return

        val url = intent.getStringExtra("url")
        val name = intent.getStringExtra("name")
        if (url != null && name != null) pokemonSelected = Pokemon(url = url, name = name)
    }

    private fun loadPokemonImage() {
        if (pokemonSelected == null) return
        Picasso.get()
                .load(getPokemonImageUrl(pokemonSelected?.id))
                .resize(400, 400)
                .centerCrop()
                .into(toolbarPokemonImage, object : com.squareup.picasso.Callback {
                    override fun onError(e: Exception?) {
                        supportStartPostponedEnterTransition();
                    }

                    override fun onSuccess() {
                        supportStartPostponedEnterTransition()
                        setPaletteColors(toolbarPokemonImage)
                    }
                })
    }

    private fun getPokemonImageUrl(id: Int?) = "http://pokeapi.co/media/sprites/pokemon/$id.png"

    private fun setPaletteColors(pokemonImage: ImageView) {
        if (pokemonImage.drawable == null) return

        val default = ContextCompat.getColor(this, android.R.color.black)
        val drawable = pokemonImage.drawable as BitmapDrawable
        val bitmap2 = drawable.bitmap
        Palette.from(bitmap2).generate { palette ->
            val toolbarColor = ContextCompat.getColor(this, R.color.colorPrimary)
            val statusColor = ContextCompat.getColor(this, R.color.colorPrimaryDark)
            animStatusBar(statusColor, palette.getDarkMutedColor(default))
            animToolbar(toolbarColor, palette.getDarkVibrantColor(default))
        }
    }

    private fun animToolbar(colorFrom: Int, colorTo: Int) {
        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), colorFrom, colorTo)
        colorAnimation.addUpdateListener { animator ->
            val color = animator.animatedValue as Int
            appBarLayout.setBackgroundColor(color)
            toolbarColapsingLayout.setBackgroundColor(color)
            toolbarColapsingLayout.setContentScrimColor(color)
        }
        colorAnimation.duration = 750
        colorAnimation.start()
    }

    private fun animStatusBar(colorFrom: Int, colorTo: Int) {
        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), colorFrom, colorTo)
        colorAnimation.addUpdateListener { animator ->
            val color = animator.animatedValue as Int
            window.statusBarColor = color
        }
        colorAnimation.duration = 1000
        colorAnimation.start()
    }
}

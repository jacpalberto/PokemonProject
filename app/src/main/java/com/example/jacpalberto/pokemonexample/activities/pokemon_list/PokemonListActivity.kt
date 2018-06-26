package com.example.jacpalberto.pokemonexample.activities.pokemon_list

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.jacpalberto.pokemonexample.R
import com.example.jacpalberto.pokemonexample.activities.pokemon_detail.PokemonDetailActivity
import com.example.jacpalberto.pokemonexample.models.Pokemon
import com.example.jacpalberto.pokemonexample.utils.PokemonActivityObserver
import kotlinx.android.synthetic.main.activity_pokemon_detail.*
import kotlinx.android.synthetic.main.activity_pokemon_list.*

/**
 * Created by Alberto Carrillo on 6/24/18.
 */

class PokemonListActivity : AppCompatActivity() {
    companion object {
        fun newIntent(context: Context) = Intent(context, PokemonListActivity::class.java)
    }

    private val onPokemonClick = { pokemon: Pokemon, view: View ->
        val transitionIntent = PokemonDetailActivity.newIntent(this, pokemon)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, *getPairList(view))
        ActivityCompat.startActivity(this, transitionIntent, options.toBundle())
    }
    private var pokemonAdapter: PokemonListAdapter = PokemonListAdapter(emptyList(), onPokemonClick)
    private var viewModel: PokemonListViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_list)
        this.lifecycle.addObserver(PokemonActivityObserver())
        viewModel = ViewModelProviders.of(this).get(PokemonListViewModel::class.java)
        init()
    }

    private fun init() {
        initToolbar()
        initRecycler()
        initObservers()
        showProgress()
        pokemonListSwipe.setOnRefreshListener { viewModel?.getPokemons() }
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.apply {
            title = getString(R.string.app_name)
            elevation = 7.0f
            setDisplayHomeAsUpEnabled(false)
            setDisplayShowHomeEnabled(false)
        }
    }

    private fun initRecycler() {
        val isPortraitScreen = resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
        pokemonRecyclerView.layoutManager = GridLayoutManager(this, if (isPortraitScreen) 2 else 4)
    }

    private fun initObservers() {
        viewModel?.getPokemons()?.observe(this, Observer { showPokemons(it) })
        viewModel?.observeMessages()?.observe(this, Observer { showMessage(it) })
    }

    private fun showMessage(message: String?) {
        dismissProgress()
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showPokemons(pokemonList: List<Pokemon>?) {
        dismissProgress()
        if (pokemonList != null) {
            pokemonAdapter = PokemonListAdapter(pokemonList, onPokemonClick)
            pokemonRecyclerView.adapter = pokemonAdapter
        }
    }

    private fun showProgress() {
        if (!pokemonListSwipe.isRefreshing) progressBar.visibility = View.VISIBLE
    }

    private fun dismissProgress() {
        progressBar.visibility = View.GONE
        pokemonListSwipe.isRefreshing = false
    }

    private fun getPairList(view: View): Array<android.support.v4.util.Pair<View, String>> {
        val navigationBar = findViewById<View>(android.R.id.navigationBarBackground)
        val statusBar = findViewById<View>(android.R.id.statusBarBackground)
        val pokemonImage = view.findViewById<ImageView>(R.id.pokemonImage)
        val pokemonName = view.findViewById<TextView>(R.id.pokemonName)
        val appBarLayout = findViewById<AppBarLayout>(R.id.appBarLayout)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val actionBar = findViewById<View>(R.id.action_bar_container)

        val pairList = mutableListOf(
                Pair(statusBar, Window.STATUS_BAR_BACKGROUND_TRANSITION_NAME),
                Pair(appBarLayout as View, "appBar"),
                Pair(toolbar as View, "toolbar"),
                Pair(pokemonImage as View, "pokemonImage"),
                Pair(pokemonName as View, "pokemonName")).apply {
            if (navigationBar != null)
                add(Pair(navigationBar, Window.NAVIGATION_BAR_BACKGROUND_TRANSITION_NAME))
            if (actionBar != null) add(Pair(actionBar, Window.NAVIGATION_BAR_BACKGROUND_TRANSITION_NAME))
        }
        return pairList.toTypedArray()
    }

}
package com.example.jacpalberto.pokemonexample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.example.jacpalberto.pokemonexample.data.BaseClient
import com.example.jacpalberto.pokemonexample.models.Pokemon
import com.example.jacpalberto.pokemonexample.models.PokemonResponse
import kotlinx.android.synthetic.main.activity_pokemon_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PokemonListActivity : AppCompatActivity() {
    companion object {
        fun newIntent(context: Context) = Intent(context, PokemonListActivity::class.java)
    }

    private var pokemonList: List<Pokemon> = emptyList()
    private lateinit var pokemonAdapter: PokemonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_list)
        init()
    }

    private fun init() {
        initRecycler()
        showProgress()
        fetchPokemons()
        pokemonListSwipe.setOnRefreshListener { fetchPokemons() }
    }

    private fun initRecycler() {
        pokemonAdapter = PokemonAdapter(pokemonList)
        pokemonRecyclerView.adapter = pokemonAdapter
        pokemonRecyclerView.setHasFixedSize(true)
    }

    private fun showProgress() {
        if (!pokemonListSwipe.isRefreshing) progressBar.visibility = View.VISIBLE
    }

    fun dismissProgress() {
        progressBar.visibility = View.GONE
        pokemonListSwipe.isRefreshing = false
    }

    private fun fetchPokemons() {
        val call = BaseClient.provideApiService().getPokemonList()
        call.enqueue(object : Callback<PokemonResponse> {
            override fun onResponse(call: Call<PokemonResponse>, response: Response<PokemonResponse>) {
                if (response.isSuccessful) {
                    val pokemonResponse = response.body()
                    if (pokemonResponse?.results != null) {
                        pokemonList = pokemonResponse.results
                        pokemonAdapter.addPokemons(pokemonList)
                    }
                } else {
                    Toast.makeText(this@PokemonListActivity, "not successful", Toast.LENGTH_SHORT).show()
                }
                dismissProgress()
            }

            override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                Toast.makeText(this@PokemonListActivity, t.localizedMessage, Toast.LENGTH_LONG).show()
                dismissProgress()
            }
        })
    }
}

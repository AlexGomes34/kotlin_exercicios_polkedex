package com.example.polkedex_exercicio.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.polkedex_exercicio.R
import com.example.polkedex_exercicio.components.CardPokemon
import com.example.polkedex_exercicio.model.Pokemons
import com.example.polkedex_exercicio.service.RetrofitFactory
import kotlinx.coroutines.launch


@Composable
fun PokemonsScreen(modifier: Modifier = Modifier) {
    var listaPokemon by remember { mutableStateOf(mutableStateListOf<Pokemons>()) }

    var valueState by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        var response = RetrofitFactory().getPokemonService().getAllPokemons()

        response.result.forEach { item ->
            val pokeObj = item.asJsonObject
            val nome = pokeObj.get("name").asString

            val pokeInfo = RetrofitFactory().getPokemonService().getPokemonDetails(nome)
            listaPokemon.add(pokeInfo)
        }
    }

    Column(modifier = modifier
        .fillMaxSize()) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .background(color = Color(	255, 99, 71)),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically){
            Spacer(modifier = Modifier.size(25.dp))
            Image(
                painter = painterResource(R.drawable.pokeboll),
                contentDescription = "POKEBOLA PNG",
                modifier = Modifier
                    .size(35.dp)
            )
            Spacer(modifier = Modifier.size(25.dp))
            Text(text = "Polkédex", fontWeight = FontWeight.ExtraBold, fontSize = 30.sp, color = Color.White)
        }
        Row(modifier = Modifier
            .padding(30.dp)
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically)
        {
            TextField(
                value = valueState,
                onValueChange = {
                    valueState = it
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {Text(text = "Nome ou ID", fontSize = 20.sp)},
                trailingIcon = {
                    IconButton(onClick = {
                        if (valueState == "" || valueState == null){
                            listaPokemon.clear()
                            scope.launch {
                                var response =
                                    RetrofitFactory().getPokemonService().getAllPokemons()

                                response.result.forEach { item ->
                                    val pokeObj = item.asJsonObject
                                    val nome = pokeObj.get("name").asString

                                    val pokeInfo = RetrofitFactory().getPokemonService()
                                        .getPokemonDetails(nome)
                                    listaPokemon.add(pokeInfo)
                                }
                            }
                        }else{
                            listaPokemon.clear()
                            scope.launch {
                                try {
                                    val Pokemon = RetrofitFactory().getPokemonService().getPokemonDetails(
                                        value = valueState
                                    )
                                    listaPokemon.add(Pokemon)
                                }catch (e: Exception){
                                    Log.i("TESTE", "${e.message}")
                                }
                            }
                        }
                    }) {
                        Icon(painter = painterResource(R.drawable.lupa),
                            contentDescription = "lupa",
                            modifier = Modifier.size(20.dp))
                    }
                }
            )
        }
        Column(modifier = Modifier.fillMaxSize()){
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(listaPokemon) {
                    // O Card do Pokémon
                    CardPokemon(it)
                }
                Log.i("TESTE", listaPokemon.toString())
            }
        }
    }
}

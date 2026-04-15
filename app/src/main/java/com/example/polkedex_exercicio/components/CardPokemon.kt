package com.example.polkedex_exercicio.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.polkedex_exercicio.model.Pokemons


@Composable
fun CardPokemon(pokemons: Pokemons){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .clip(RoundedCornerShape(12.dp))
            .border(1.dp, Color.Green, shape = RoundedCornerShape(12.dp)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Box para organizar o ID (Top End) e a Imagem (Center)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f) // Ocupa o espaço disponível acima da tarja verde
                .padding(4.dp)
        ) {
            Text(
                text = "${pokemons.id}",
                fontSize = 10.sp,
                color = Color.Gray,
                modifier = Modifier.align(Alignment.TopEnd) // Canto superior direito
            )

            val spritesObj = pokemons.sprites

            val imagemFodona = spritesObj
                .getAsJsonObject("other")
                ?.getAsJsonObject("official-artwork")
                ?.get("front_default")?.asString

            AsyncImage(
                model = imagemFodona,
                contentDescription = "Pokemon",
                modifier = Modifier
                    .size(70.dp)
                    .align(Alignment.Center) // Imagem centralizada no Box
            )
        }

        // Rodapé com o nome
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
                .background(Color.Green),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${pokemons.name}",
                fontSize = 14.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

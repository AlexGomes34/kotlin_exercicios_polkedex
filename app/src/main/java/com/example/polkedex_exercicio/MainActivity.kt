package com.example.polkedex_exercicio

import android.content.ClipData
import com.example.polkedex_exercicio.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.polkedex_exercicio.ui.theme.Polkedex_ExercicioTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Polkedex_ExercicioTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PokemonsScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun PokemonsScreen(modifier: Modifier = Modifier) {

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
                value = "",
                onValueChange = {

                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {Text(text = "Nome ou ID", fontSize = 20.sp)},
                trailingIcon = {
                    Icon(painter = painterResource(R.drawable.lupa),
                        contentDescription = "lupa",
                        modifier = Modifier.size(20.dp))
                }
            )
        }
        Column(modifier = Modifier.fillMaxSize()
            .padding(start = 10.dp)){
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(9) { // Aumentei para 9 para testar a rolagem
                    // O Card do Pokemon
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
                                text = "#001",
                                fontSize = 10.sp,
                                color = Color.Gray,
                                modifier = Modifier.align(Alignment.TopEnd) // Canto superior direito
                            )

                            Image(
                                painter = painterResource(R.drawable.lupa),
                                contentDescription = "pokemonk",
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
                                text = "Bulbasaur",
                                fontSize = 14.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
        }
    }

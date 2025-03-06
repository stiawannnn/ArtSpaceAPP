package com.example.artspaceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Membuat data class untuk menampung detail artwork
data class Artwork(
    val title: String,
    val author: String,
    val year: String,
    val imageRes: Int
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceApp()
        }
    }
}

@Composable
fun ArtSpaceApp() {
    // Daftar artwork dengan detail nama, autor, dan tahun
    val artworks = listOf(
        Artwork("Starry Night", "Vincent Van Gogh", "1889", R.drawable.starynight),
        Artwork("Mona Lisa", "Leonardo Da Vinci", "1503", R.drawable.monalisa),
        Artwork("The Scream", "Edvard Munch", "1893", R.drawable.scream)
    )

    var currentIndex by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Konten utama: gambar dan detail artwork di tengah layar
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = artworks[currentIndex].imageRes),
                contentDescription = "Artwork",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
            )
            Spacer(modifier = Modifier.height(30.dp))
            // Menampilkan detail dengan styling


            Text(
                text = artworks[currentIndex].title,
                fontSize = 24.sp,
                textAlign = TextAlign.Center

            )

            Text(
                text = "By ${artworks[currentIndex].author}",
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Year: ${artworks[currentIndex].year}",
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
        }

        // Tombol navigasi untuk berpindah artwork
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = { if (currentIndex > 0) currentIndex-- }) {
                Text("Previous")
            }
            Button(onClick = { if (currentIndex < artworks.size - 1) currentIndex++ }) {
                Text("Next")
            }
        }
    }
}

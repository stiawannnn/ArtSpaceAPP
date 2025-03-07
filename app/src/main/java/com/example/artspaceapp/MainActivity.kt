package com.example.artspaceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Button
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.ButtonDefaults
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color


data class Artwork(
    val title: String,
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
    val artworks = listOf(
        Artwork(
            "SIMATIF 2023",
            R.drawable.kegiatan1

        ),
        Artwork
            (
            "Seminar English Communication Skill",
            R.drawable.kegiatan2),
        Artwork
            ("PBAK Fakultas 2023",
            R.drawable.kegiatan3),

        Artwork(
            "Kegiatan Milad TIF-24",
            R.drawable.kegiatan4),

        Artwork(
            "Acara Bakar Ayam Sekelas",
            R.drawable.kegiatan5
        ),
    )

    var currentIndex by remember { mutableIntStateOf(0) }

    val swipeGesture = Modifier.pointerInput(Unit) {
        detectHorizontalDragGestures { change, dragAmount ->
            change.consume()
            if (dragAmount < -50 && currentIndex < artworks.size - 1) {
                currentIndex++
            } else if (dragAmount > 50 && currentIndex > 0) {
                currentIndex--
            }
        }
    }
    val backgroundColor = Color(0xFFE0E0E0)


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor).padding(3.dp)
            .then(swipeGesture)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Daftar 5 Foto Kegiatan",
                fontSize = 38.sp,
                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                color = Color.Black,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 14.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Image(
                painter = painterResource(id = artworks[currentIndex].imageRes),
                contentDescription = "Artwork",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
            )

            Spacer(modifier = Modifier.height(30.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF5A5A5A), shape = RoundedCornerShape(400.dp))
                    .padding(9.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = artworks[currentIndex].title,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center ,color = Color.White
                    )

                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { if (currentIndex > 0) currentIndex-- },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF5A5A5A))
            ) {
                Text("Previous", color = Color.White)
            }

            Button(
                onClick = { if (currentIndex < artworks.size - 1) currentIndex++ },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF5A5A5A))

            ) {
                Text("Next", color = Color.White)
            }

        }
    }
}


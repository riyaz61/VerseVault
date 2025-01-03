package com.example.versevalult.UserINterface.Components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.google.gson.Gson

import com.example.versevalult.R

@Composable
fun PoemCard(
    imageResId: String, // URL for the image
    poemName: String,
    authorName: String,
    modifier: Modifier = Modifier, // Allow custom modifiers
    lines: List<String>,
    navcontroller: NavHostController
) {
    val gson = Gson()
    val linesJson = gson.toJson(lines)
    Log.d("Poem_day", "PoemCard: " + imageResId + "   " + authorName+ "   " + lines)
    Card(
        modifier = modifier
            .width(250.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable {
                navcontroller.navigate("Poem_Details/${linesJson}/${poemName}")
            },
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF121212) // Dark background for the card
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Image on top
            AsyncImage(
                model = imageResId,
                error = painterResource(id =R.drawable.login),

                contentDescription = "Poem Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
            )


            Spacer(modifier = Modifier.height(16.dp))

            // Poem name
            Text(
                text = poemName,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Author name
            Text(
                text = "by $authorName",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 14.sp,
                    color = Color(0xFFBBBBBB) // Light gray for better contrast
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
@Preview
fun PoemCardPreview() {
    PoemCard(
        imageResId = "https://image.pollinations.ai/prompt/user3", // Replace with an actual URL
        poemName = "The Road Not Taken",
        authorName = "Robert Frost",
        modifier = Modifier
            .padding(16.dp),
        lines = emptyList(),
        navcontroller = rememberNavController()

    )
}

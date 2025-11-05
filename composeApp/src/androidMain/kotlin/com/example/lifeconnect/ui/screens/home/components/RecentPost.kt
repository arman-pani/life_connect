package com.example.lifeconnect.ui.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.lifeconnect.R

//@Preview(showBackground = true)
@Composable
fun RecentPost(){
    val posts = listOf<RecentPostDetails>(
        RecentPostDetails(
            title = "Who can give blood? - WHO",
            image = R.drawable.ic_launcher_background
        ),
        RecentPostDetails(
            title = "Who can give blood? - WHO",
            image = R.drawable.ic_launcher_background
        ),
        RecentPostDetails(
            title = "Who can give blood? - WHO",
            image = R.drawable.ic_launcher_background
        ),
        RecentPostDetails(
            title = "Who can give blood? - WHO",
            image = R.drawable.ic_launcher_background
        ),
        RecentPostDetails(
            title = "Who can give blood? - WHO",
            image = R.drawable.ic_launcher_background
        )
    )
    Column (
        modifier = Modifier.fillMaxWidth().padding(start = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ){
        Text("Recent Post", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(posts) { item ->
                PostCard(title = item.title, image = item.image)
            }
        }
    }
}

data class RecentPostDetails(
    val title: String,
    val image: Int,
)

@Composable
fun PostCard(title: String, image: Int){
    Box (
        modifier = Modifier
            .width(175.dp)
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = "Example image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(10.dp))
            )
            Text(
                title,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
        }

    }
}
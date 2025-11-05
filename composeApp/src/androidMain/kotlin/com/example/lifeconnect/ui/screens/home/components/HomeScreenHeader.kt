package com.example.lifeconnect.ui.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.lifeconnect.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenHeader(){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Red.copy(alpha = 0.1f)
        ),
        scrollBehavior = scrollBehavior,
        expandedHeight = 80.dp,
        navigationIcon = {
            Box(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .size(56.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = "Profile image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.clip(CircleShape)
                )
            }
        },
        title = {
            Column(
                modifier = Modifier.padding(horizontal = 10.dp)
            ) {
                Text("Arman Pani", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Text("Donate Blood: Off", style = MaterialTheme.typography.bodySmall)
            }
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Outlined.Email,
                    contentDescription = "message",
                )
            }
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Outlined.Notifications,
                    contentDescription = "message",
                )
            }
        }
    )
}
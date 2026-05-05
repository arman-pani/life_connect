package com.example.lifeconnect.ui.screens.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.example.lifeconnect.ui.auth.UserViewModel
import org.koin.compose.viewmodel.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenHeader(
    viewModel: UserViewModel = koinViewModel()
){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val user = viewModel.user.collectAsStateWithLifecycle().value
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
                AsyncImage(
                    contentDescription = "Profile image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.clip(CircleShape),
                    model = user?.profilePhoto
                )
            }
        },
        title = {
            Column(
                modifier = Modifier.padding(horizontal = 10.dp)
            ) {
                user?.name?.let { Text(it, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold) }
                Text("Donate Blood: ${ if(user?.isDonater == true)  "Yes" else "No"}", style = MaterialTheme.typography.bodySmall)
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
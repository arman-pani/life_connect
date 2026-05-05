package com.example.lifeconnect.ui.screens.profile.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HeartBroken
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material.icons.filled.Settings

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lifeconnect.ui.screens.profile.components.ProfileCard
import com.example.lifeconnect.ui.screens.profile.components.ProfileData
import com.example.lifeconnect.ui.screens.profile.components.ProfilePlaceholderId
import com.example.lifeconnect.ui.screens.profile.components.RowButton
import com.example.lifeconnect.ui.screens.profile.models.MenuRowModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
fun ProfileScreen(modifier: Modifier = Modifier){
    val menuItems = listOf(
        MenuRowModel("FAQ", Icons.Filled.QuestionMark) { println("Orders clicked") },
        MenuRowModel("Settings", Icons.Filled.Settings) { println("Settings clicked") },
        MenuRowModel("Donate Us", Icons.Filled.HeartBroken) { println("Settings clicked") }
    )
        Column (
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            ProfileCard(
                onEditClick = {},
                profile = ProfileData(
                    name = "Brooklyn Simmons",
                    bloodGroup = "B+",
                    profileImageResId = ProfilePlaceholderId
                )
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(
                    items = menuItems
                ) {item ->
                    RowButton(
                        label = item.label,
                        onClick = item.onClick,
                        icon = item.icon
                    )
                }
            }
        }
}
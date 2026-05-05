package com.example.lifeconnect.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.lifeconnect.ui.navigation.BottomDestination

@Composable
fun BottomNavBar(
    navController: NavHostController,
    selectedDestination: Int,
    setSelectedDestination: (Int) -> Unit
){
    NavigationBar (
        containerColor = Color.White,
        contentColor = Color.Red,
        tonalElevation = 50.dp,
        windowInsets = NavigationBarDefaults.windowInsets) {
        BottomDestination.entries.forEachIndexed { index, destination ->
            NavigationBarItem(
                colors = NavigationBarItemColors(
                    selectedIconColor = Color.Red,
                    selectedTextColor = Color.Red,
                    unselectedIconColor = Color.Red.copy(alpha = 0.5f),
                    selectedIndicatorColor = Color.Red.copy(alpha = 0.1f),
                    unselectedTextColor = Color.Red.copy(alpha = 0.5f),
                    disabledIconColor = Color.Red.copy(alpha = 0.1f),
                    disabledTextColor = Color.Red.copy(alpha = 0.1f),
                ),
                selected = selectedDestination == index,
                onClick = {
                    navController.navigate(route = destination.route)
                    setSelectedDestination(index)
                },
                icon = {
                    Icon(
                        imageVector = destination.icon,
                        contentDescription = destination.contentDescription
                    )
                },
                label = {
                    Text(destination.label)
                }
            )
        }
    }
}
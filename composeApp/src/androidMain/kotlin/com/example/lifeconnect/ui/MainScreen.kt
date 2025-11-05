package com.example.lifeconnect.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.lifeconnect.ui.navigation.BottomDestination
import com.example.lifeconnect.ui.navigation.NavGraph
import com.example.lifeconnect.ui.screens.home.components.HomeScreenHeader

@Preview
@Composable
fun MainScreen(){
    val navController = rememberNavController()
    val startDestination = BottomDestination.HOME
    var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }

    Scaffold (
        topBar = { HomeScreenHeader() },
        bottomBar = {
            NavigationBar (
                containerColor = Color.White,
                contentColor = Color.Red,
                tonalElevation = 50.dp,
                windowInsets = NavigationBarDefaults.windowInsets){
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
                            selectedDestination = index
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
    ){ innerPadding ->
        NavGraph(
            navController,
            startDestination,
            modifier = Modifier.padding(innerPadding).fillMaxSize()
        )
    }
}


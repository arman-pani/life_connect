package com.example.lifeconnect.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Bloodtype
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.lifeconnect.ui.screens.home.HomeScreen


@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = BottomDestination.HOME.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(BottomDestination.HOME.route) { HomeScreen() }
        requestNavGraph(navController)
        profileNavGraph(navController)
    }
}


enum class BottomDestination(
    val route: String,
    val label: String,
    val icon: ImageVector,
    val contentDescription: String
) {
    HOME("home", "Home", Icons.Default.Home, "Home"),
    BLOOD_REQUESTS("bloodRequestsGraph", "Requests", Icons.Default.Bloodtype, "Blood Requests"),
    PROFILE("profileGraph", "Profile", Icons.Default.AccountCircle, "Profile")
}

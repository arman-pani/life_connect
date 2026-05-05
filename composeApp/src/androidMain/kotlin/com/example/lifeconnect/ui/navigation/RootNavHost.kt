package com.example.lifeconnect.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lifeconnect.ui.MainScreen
import com.example.lifeconnect.ui.screens.LoadingScreen
import com.example.lifeconnect.viewModels.MainViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun RootNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    viewModel: MainViewModel = koinViewModel(),
){
    val isOnboardingComplete by viewModel.isOnboardingComplete.collectAsState()

    if (isOnboardingComplete == null){
        return LoadingScreen()
    } else {
        NavHost(
            navController = navController,
            startDestination = if (isOnboardingComplete == true) RootDestination.MAIN.route else RootDestination.AUTH.route,
            modifier = modifier
        ){
            authNavGraph(navController)
            composable (RootDestination.MAIN.route) { MainScreen() }
        }
    }

}


enum class RootDestination(
    val route: String,
    val title: String,
) {
    AUTH("auth", "Auth"),
    MAIN("main", "Main"),
}
package com.example.lifeconnect.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lifeconnect.ui.components.BottomNavBar
import com.example.lifeconnect.ui.navigation.AppNavHost
import com.example.lifeconnect.ui.navigation.BottomDestination
import com.example.lifeconnect.ui.navigation.ProfileSubScreen
import com.example.lifeconnect.ui.navigation.RequestSubScreen
import com.example.lifeconnect.ui.screens.home.components.HomeScreenHeader
import com.example.lifeconnect.ui.screens.profile.components.DefaultAppBar
import com.example.lifeconnect.ui.screens.requests.components.CreateRequestFAB

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun MainScreen(){
    val navController = rememberNavController()
    val startDestination = BottomDestination.HOME
    var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val currentDestination = BottomDestination.entries.find {
        it.route == currentRoute
    }

    val bottomBarRoutes = remember {
        // FIX: Include the start destination of the nested graph
        BottomDestination.entries.map { it.route }.toMutableSet().apply {
            add(ProfileSubScreen.PROFILE.route) // ADD THIS LINE 🔑
            add(RequestSubScreen.BLOOD_REQUESTS.route)
        }
    }

    val showHomeBar = currentRoute == BottomDestination.HOME.route
    val showBottomBar = currentRoute in bottomBarRoutes || currentRoute?.startsWith(BottomDestination.PROFILE.route) == true
    val showRequestFAB = currentRoute == RequestSubScreen.BLOOD_REQUESTS.route


    Scaffold (
        topBar = { if (showHomeBar) { HomeScreenHeader()}},
        bottomBar = { if (showBottomBar) {
                BottomNavBar(navController, selectedDestination) { selectedDestination = it }
            }
        },
        floatingActionButton = { if (showRequestFAB)
            CreateRequestFAB {
                navController.navigate(RequestSubScreen.CREATE_REQUEST.route)
            }
        },
    ){ innerPadding ->
        val bottomPadding = innerPadding.calculateBottomPadding()
        val horizontalPadding = innerPadding.calculateLeftPadding(LayoutDirection.Ltr) +
                innerPadding.calculateRightPadding(LayoutDirection.Ltr)

        AppNavHost(
            navController,
            modifier = Modifier
                .padding(bottom = bottomPadding, start = horizontalPadding, end = horizontalPadding)
                .fillMaxSize()
                .background(color = Color.White)
        )
    }
}


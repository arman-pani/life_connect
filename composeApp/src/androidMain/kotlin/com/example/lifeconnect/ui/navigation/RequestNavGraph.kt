package com.example.lifeconnect.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.lifeconnect.ui.screens.requests.screens.BloodRequestScreen
import com.example.lifeconnect.ui.screens.requests.screens.CreateRequestScreen
import com.example.lifeconnect.ui.screens.requests.screens.LocationPickerScreen
import com.example.lifeconnect.ui.screens.requests.screens.RequestDetailsScreen

enum class RequestSubScreen (
    val route: String,
    val title: String,
) {
    BLOOD_REQUESTS("bloodRequests", "Blood Requests"),
    REQUEST_DETAILS("requestDetails", "Blood Request Details"),
    CREATE_REQUEST("createRequest", "Create Request"),
    LOCATION_PICKER("locationPicker", "Location Picker"),
}

fun NavGraphBuilder.requestNavGraph(
    navController: NavHostController,
){
    navigation(
        startDestination = RequestSubScreen.BLOOD_REQUESTS.route,
        route = BottomDestination.BLOOD_REQUESTS.route
    ) {
        composable(RequestSubScreen.BLOOD_REQUESTS.route) {
            BloodRequestScreen(navController = navController)
        }

        composable(RequestSubScreen.REQUEST_DETAILS.route) {
            RequestDetailsScreen(navController = navController)
        }

        composable(RequestSubScreen.CREATE_REQUEST.route) {
            CreateRequestScreen(navController = navController)
        }

        composable(RequestSubScreen.LOCATION_PICKER.route) {
            LocationPickerScreen(navController = navController)
        }
    }
}
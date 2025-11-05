package com.example.lifeconnect.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.lifeconnect.ui.screens.BloodDonationScreen
import com.example.lifeconnect.ui.screens.requests.BloodRequestScreen
import com.example.lifeconnect.ui.screens.ProfileScreen
import com.example.lifeconnect.ui.screens.home.HomeScreen
import com.example.lifeconnect.ui.screens.requests.RequestDetailsScreen


@Composable
fun NavGraph(navController: NavHostController, startDestination: BottomDestination, modifier: Modifier = Modifier){
    NavHost(
        navController = navController,
        startDestination = startDestination.route
    ) {
        BottomDestination.entries.forEach { destination ->
            composable(destination.route) {
                when(destination){
                    BottomDestination.HOME -> HomeScreen(modifier)
                    BottomDestination.BLOODREQUEST -> BloodRequestScreen(modifier, navController)
                    BottomDestination.BLOODDONATION -> BloodDonationScreen(modifier)
                    BottomDestination.PROFILE -> ProfileScreen(modifier)
                }
            }
        }

        composable(IndependentDestination.BLOODREQUESTDETAILS.route) { RequestDetailsScreen() }
        composable(IndependentDestination.PROFILESETUP.route) { ProfileScreen() }
    }
}

enum class BottomDestination(
    val route: String,
    val label: String,
    val icon: ImageVector,
    val contentDescription: String
) {
    HOME("home", "Home", Icons.Default.Home, "Home"),
    BLOODREQUEST("bloodRequest", "Requests", Icons.Default.Place, "Blood Requests"),
    BLOODDONATION("bloodDonation", "Donations", Icons.Default.Info, "Blood Donations"),
    PROFILE("profile", "Profile", Icons.Default.AccountCircle, "Profile")

}

enum class IndependentDestination(
    val route: String,
    val label: String,
) {
    BLOODREQUESTDETAILS("bloodRequestDetails", "Blood Request Details"),
    PROFILESETUP("profileSetup", "Profile Setup"),
    DONATIONDETAILS("donationDetails", "Donation Details")
}


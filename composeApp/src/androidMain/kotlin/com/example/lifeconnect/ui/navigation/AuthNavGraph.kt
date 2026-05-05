package com.example.lifeconnect.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Login
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Bloodtype
import androidx.compose.material.icons.filled.Verified
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.lifeconnect.ui.screens.onboarding.screens.LoginScreen
import com.example.lifeconnect.ui.screens.onboarding.screens.OnboardingScreen
import com.example.lifeconnect.ui.screens.onboarding.screens.VerificationScreen
import com.example.lifeconnect.ui.screens.onboarding.screens.ProfileSetupScreen


fun NavGraphBuilder.authNavGraph(
    navController: NavHostController
) {
    navigation(
        route = RootDestination.AUTH.route,
        startDestination = AuthDestination.ONBOARDING.route
    ) {
        composable(AuthDestination.ONBOARDING.route) {
            OnboardingScreen(
                onContinue = {
                    navController.navigate(AuthDestination.LOGIN.route)
                }
            )
        }

        composable(AuthDestination.LOGIN.route) {
            LoginScreen(
                onContinue = {
                    navController.navigate(AuthDestination.VERIFICATION.route)
                }
            )
        }

        composable(AuthDestination.VERIFICATION.route) {
            VerificationScreen(
                onSuccess = {
                    navController.navigate(AuthDestination.PROFILE_SETUP.route)
                }
            )
        }
        composable(AuthDestination.PROFILE_SETUP.route) {
            ProfileSetupScreen(
                onSuccess = {
                    // Navigate to Home and permanently erase onboarding from navigation history.
                    navController.navigate(RootDestination.MAIN.route){
                        popUpTo(RootDestination.AUTH.route){
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}

enum class AuthDestination(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    ONBOARDING("onboarding", "Onboarding", Icons.Default.Bloodtype),
    LOGIN("login", "Login", Icons.AutoMirrored.Filled.Login),
    VERIFICATION("verification", "Verification", Icons.Default.Verified),
    PROFILE_SETUP("profileSetup", "Profile Setup", Icons.Default.AccountCircle),
}

package com.example.lifeconnect.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Article
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.HeartBroken
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.lifeconnect.ui.screens.profile.screens.BlogScreen
import com.example.lifeconnect.ui.screens.profile.screens.DonateUsScreen
import com.example.lifeconnect.ui.screens.profile.screens.EditProfileScreen
import com.example.lifeconnect.ui.screens.profile.screens.FAQScreen
import com.example.lifeconnect.ui.screens.profile.screens.ProfileScreen
import com.example.lifeconnect.ui.screens.profile.screens.SettingsScreen


fun NavGraphBuilder.profileNavGraph(
    navController: NavHostController,
){
    navigation(
        startDestination = ProfileSubScreen.PROFILE.route,
        route = BottomDestination.PROFILE.route
    ) {
        composable(ProfileSubScreen.PROFILE.route) {
            ProfileScreen(navController = navController)
        }
        composable(ProfileSubScreen.EDIT_PROFILE.route) {
            EditProfileScreen(onBackClick = { navController.popBackStack() })
        }

        composable(ProfileSubScreen.BLOG.route) {
            BlogScreen(onBackClick = { navController.popBackStack() })
        }
        composable(ProfileSubScreen.SETTINGS.route) {
            SettingsScreen(onBackClick = { navController.popBackStack() })
        }

        composable(ProfileSubScreen.DONATE_US.route) {
            DonateUsScreen(onBackClick = { navController.popBackStack() })
        }
        composable(ProfileSubScreen.FAQ.route) {
            FAQScreen(onBackClick = { navController.popBackStack() })
        }
    }
}

enum class ProfileSubScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    PROFILE("profile", "Profile", Icons.Default.Person),
    FAQ("faq", "FAQ", Icons.Default.QuestionMark),
    BLOG("blog", "Blog", Icons.AutoMirrored.Filled.Article),
    SETTINGS("settings", "Settings", Icons.Default.Settings),
    DONATE_US("donateUs", "Donate Us", Icons.Default.HeartBroken),
    EDIT_PROFILE("editProfile", "Edit Profile", Icons.Default.Edit)
}
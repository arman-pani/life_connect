package com.example.lifeconnect.ui.screens.profile.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lifeconnect.ui.auth.UserViewModel
import com.example.lifeconnect.ui.navigation.ProfileSubScreen
import com.example.lifeconnect.ui.screens.profile.components.ProfileCard
import com.example.lifeconnect.ui.screens.profile.components.RowButton
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController(),
    viewModel: UserViewModel = koinViewModel(),
){
    val menuItems = listOf(
        ProfileSubScreen.BLOG,
        ProfileSubScreen.SETTINGS,
        ProfileSubScreen.DONATE_US,
        ProfileSubScreen.FAQ
    )
    val state = viewModel.user.collectAsStateWithLifecycle()
        Column (
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black
                ),
                title = { Text("Profile") },
            )
            state.value?.let {
                ProfileCard(
                    onEditClick = {
                        navController.navigate(ProfileSubScreen.EDIT_PROFILE.route)
                    },
                    user = it
                )
            }

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(
                    items = menuItems
                ) {item ->
                    RowButton(
                        label = item.title,
                        onClick = {navController.navigate(item.route)},
                        icon = item.icon
                    )
                }
            }
        }
}
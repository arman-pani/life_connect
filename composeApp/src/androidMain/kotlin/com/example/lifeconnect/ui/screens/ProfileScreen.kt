package com.example.lifeconnect.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ProfileScreen(modifier: Modifier = Modifier){
        Column (
            modifier = modifier.fillMaxSize()
        ){
            Text("Profile Screen")
        }
}
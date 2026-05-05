package com.example.lifeconnect.ui.screens.requests.components

import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun CreateRequestFAB(
    onClick: () -> Unit
){
    ExtendedFloatingActionButton(
        onClick = onClick,
        containerColor = Color.Red,
        contentColor = Color.White,
        icon = {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "New Request",
            )
        },
        text = {
            Text(
                text = "New Request",
            )
        }
    )
}
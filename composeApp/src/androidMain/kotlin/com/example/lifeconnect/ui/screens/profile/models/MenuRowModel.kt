package com.example.lifeconnect.ui.screens.profile.models

import androidx.compose.ui.graphics.vector.ImageVector

data class MenuRowModel(
    val label: String,
    val icon: ImageVector,
    val onClick: () -> Unit
)
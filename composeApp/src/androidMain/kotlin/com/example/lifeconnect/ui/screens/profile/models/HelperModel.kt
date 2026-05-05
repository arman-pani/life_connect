package com.example.lifeconnect.ui.screens.profile.models

import androidx.compose.ui.graphics.painter.Painter

data class HelperModel(
    val id: Int,
    val name: String,
    val contactNumber: String,
    val profileImagePainter: Painter
)
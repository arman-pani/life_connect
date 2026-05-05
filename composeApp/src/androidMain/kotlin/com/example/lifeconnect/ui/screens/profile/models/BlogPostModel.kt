package com.example.lifeconnect.ui.screens.profile.models

import androidx.compose.ui.graphics.painter.Painter

data class BlogPostModel(
    val id: Int,
    val title: String,
    val date: String,
    val imagePainter: Painter
)
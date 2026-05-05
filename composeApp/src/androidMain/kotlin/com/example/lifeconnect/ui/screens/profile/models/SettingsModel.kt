package com.example.lifeconnect.ui.screens.profile.models

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

// Base interface for a settings item
sealed class SettingsItem {
    abstract val label: String
    abstract val icon: ImageVector
    abstract val iconBackgroundColor: Color
    abstract val iconTintColor: Color
}

// For items that navigate (e.g., Language, Privacy Policy)
data class NavigableSettingsItem(
    override val label: String,
    override val icon: ImageVector,
    override val iconBackgroundColor: Color,
    override val iconTintColor: Color,
    val onClick: () -> Unit
) : SettingsItem()

// For items with a toggle switch (e.g., Mood, Notifications)
data class ToggleSettingsItem(
    override val label: String,
    override val icon: ImageVector,
    override val iconBackgroundColor: Color,
    override val iconTintColor: Color,
    val isChecked: Boolean,
    val onToggle: (Boolean) -> Unit
) : SettingsItem()
package com.example.lifeconnect.ui.screens.profile

import androidx.compose.ui.graphics.vector.ImageVector

// Base interface for a settings item
sealed class SettingsItem {
    abstract val label: String
    abstract val icon: ImageVector
    abstract val iconBackgroundColor: androidx.compose.ui.graphics.Color
    abstract val iconTintColor: androidx.compose.ui.graphics.Color
}

// For items that navigate (e.g., Language, Privacy Policy)
data class NavigableSettingsItem(
    override val label: String,
    override val icon: ImageVector,
    override val iconBackgroundColor: androidx.compose.ui.graphics.Color,
    override val iconTintColor: androidx.compose.ui.graphics.Color,
    val onClick: () -> Unit
) : SettingsItem()

// For items with a toggle switch (e.g., Mood, Notifications)
data class ToggleSettingsItem(
    override val label: String,
    override val icon: ImageVector,
    override val iconBackgroundColor: androidx.compose.ui.graphics.Color,
    override val iconTintColor: androidx.compose.ui.graphics.Color,
    val isChecked: Boolean,
    val onToggle: (Boolean) -> Unit
) : SettingsItem()
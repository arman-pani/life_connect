package com.example.lifeconnect.ui.screens.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.lifeconnect.ui.screens.profile.models.NavigableSettingsItem
import com.example.lifeconnect.ui.screens.profile.models.ToggleSettingsItem

// --- Reusable Composable for the Icon Background ---
@Composable
fun SettingsIconContainer(
    icon: ImageVector,
    backgroundColor: Color,
    tintColor: Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(36.dp) // Fixed size for the circle
            .background(color = backgroundColor, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null, // Content description for accessibility
            tint = tintColor,
            modifier = Modifier.size(20.dp) // Size of the actual icon
        )
    }
}

// --- Composable for a Navigable Settings Row ---
@Composable
fun NavigableSettingsRow(
    item: NavigableSettingsItem,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { item.onClick() } // Make the whole row clickable
            .padding(vertical = 12.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        SettingsIconContainer(
            icon = item.icon,
            backgroundColor = item.iconBackgroundColor,
            tintColor = item.iconTintColor
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = item.label,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f) // Push content to the right
        )

        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowRight,
            contentDescription = null, // Decorative icon
            tint = Color.Gray,
            modifier = Modifier.size(20.dp)
        )
    }
}

// --- Composable for a Toggle Settings Row ---
@Composable
fun ToggleSettingsRow(
    item: ToggleSettingsItem,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { item.onToggle(!item.isChecked) } // Make row clickable for toggle
            .padding(vertical = 12.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        SettingsIconContainer(
            icon = item.icon,
            backgroundColor = item.iconBackgroundColor,
            tintColor = item.iconTintColor
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = item.label,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f) // Push content to the right
        )

        Switch(
            checked = item.isChecked,
            onCheckedChange = item.onToggle, // This handles the actual toggle
            colors = SwitchDefaults.colors(
                checkedTrackColor = Color(0xFFFF4D67), // Red color for checked state
                checkedThumbColor = Color.White
            )
        )
    }
}
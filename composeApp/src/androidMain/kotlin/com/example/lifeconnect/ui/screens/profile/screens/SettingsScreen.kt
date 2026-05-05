package com.example.lifeconnect.ui.screens.profile.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.* // Import all filled icons for convenience
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import android.widget.Toast // For Toast messages
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.lifeconnect.ui.screens.profile.components.DefaultAppBar
import com.example.lifeconnect.ui.screens.profile.components.NavigableSettingsRow
import com.example.lifeconnect.ui.screens.profile.components.ToggleSettingsRow
import com.example.lifeconnect.ui.screens.profile.models.NavigableSettingsItem
import com.example.lifeconnect.ui.screens.profile.models.SettingsItem
import com.example.lifeconnect.ui.screens.profile.models.ToggleSettingsItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(modifier: Modifier = Modifier, onBackClick: () -> Unit = {}) {
    val context = LocalContext.current // To show toasts

    // State for toggle items
    var moodToggleState by remember { mutableStateOf(false) }
    var notificationToggleState by remember { mutableStateOf(true) }

    // Define your settings items
    val settingsItems: List<SettingsItem> = listOf(
        NavigableSettingsItem(
            label = "Language",
            icon = Icons.Default.Language,
            iconBackgroundColor = Color(0xFFFEE8D3), // Light orange
            iconTintColor = Color(0xFFFFA500),      // Orange
            onClick = { Toast.makeText(context, "Language clicked", Toast.LENGTH_SHORT).show() }
        ),
        NavigableSettingsItem(
            label = "Country",
            icon = Icons.Default.LocationOn,
            iconBackgroundColor = Color(0xFFD3E0FC), // Light blue
            iconTintColor = Color(0xFF4A90E2),       // Blue
            onClick = { Toast.makeText(context, "Country clicked", Toast.LENGTH_SHORT).show() }
        ),
        ToggleSettingsItem(
            label = "Mood",
            icon = Icons.Default.Mood,
            iconBackgroundColor = Color(0xFFE0F7FA), // Light teal
            iconTintColor = Color(0xFF009688),       // Teal
            isChecked = moodToggleState,
            onToggle = { newValue ->
                moodToggleState = newValue
                Toast.makeText(context, "Mood: $newValue", Toast.LENGTH_SHORT).show()
            }
        ),
        ToggleSettingsItem(
            label = "Notification Received",
            icon = Icons.Default.Notifications,
            iconBackgroundColor = Color(0xFFFCE4EC), // Light pink
            iconTintColor = Color(0xFFE91E63),       // Pink
            isChecked = notificationToggleState,
            onToggle = { newValue ->
                notificationToggleState = newValue
                Toast.makeText(context, "Notifications: $newValue", Toast.LENGTH_SHORT).show()
            }
        ),
        NavigableSettingsItem(
            label = "Privacy Policy",
            icon = Icons.Default.PrivacyTip,
            iconBackgroundColor = Color(0xFFFFFDE7), // Light yellow
            iconTintColor = Color(0xFFFFEB3B),       // Yellow
            onClick = {
                Toast.makeText(context, "Privacy Policy clicked", Toast.LENGTH_SHORT).show()
            }
        ),
        NavigableSettingsItem(
            label = "Rate Us",
            icon = Icons.Default.Star,
            iconBackgroundColor = Color(0xFFE3F2FD), // Light blue
            iconTintColor = Color(0xFF2196F3),       // Blue
            onClick = { Toast.makeText(context, "Rate Us clicked", Toast.LENGTH_SHORT).show() }
        ),
        NavigableSettingsItem(
            label = "Share App",
            icon = Icons.AutoMirrored.Filled.Send, // For a share icon
            iconBackgroundColor = Color(0xFFDCEDC8), // Light green
            iconTintColor = Color(0xFF8BC34A),       // Green
            onClick = { Toast.makeText(context, "Share App clicked", Toast.LENGTH_SHORT).show() }
        ),
        NavigableSettingsItem(
            label = "Delete Account",
            icon = Icons.Default.Delete,
            iconBackgroundColor = Color(0xFFFFEBEE), // Light red
            iconTintColor = Color(0xFFF44336),       // Red
            onClick = {
                Toast.makeText(context, "Delete Account clicked", Toast.LENGTH_SHORT).show()
            }
        ),
        NavigableSettingsItem(
            label = "Logout",
            icon = Icons.AutoMirrored.Filled.ExitToApp, // For logout icon
            iconBackgroundColor = Color(0xFFF3E5F5), // Light purple
            iconTintColor = Color(0xFF9C27B0),       // Purple
            onClick = { Toast.makeText(context, "Logout clicked", Toast.LENGTH_SHORT).show() }
        )
    )

    Scaffold(
        topBar = {
            DefaultAppBar(
                title = "Settings",
                onBackClick
            )
        },
        containerColor = MaterialTheme.colorScheme.background // Set screen background
    ) { paddingValues ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues), // Apply padding from Scaffold
            verticalArrangement = Arrangement.spacedBy(4.dp) // Small spacing between rows
        ) {
            items(items = settingsItems) { item ->
                when (item) {
                    is NavigableSettingsItem -> NavigableSettingsRow(item = item)
                    is ToggleSettingsItem -> ToggleSettingsRow(item = item)
                }
                HorizontalDivider(color = Color(0xFFE0E0E0), thickness = 0.5.dp) // Divider below each item
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSettingsScreen() {
    MaterialTheme { // Use your app's theme here
        SettingsScreen()
    }
}
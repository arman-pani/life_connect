package com.example.lifeconnect.ui.screens.profile.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

// Data structure for an FAQ item
data class FAQItemModel(
    val id: Int,
    val question: String,
    val answer: String
)

@Composable
fun FAQItem(item: FAQItemModel, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp) // Padding matches the design
    ) {
        // --- Question/Title (Bold and Numbered) ---
        Text(
            // Format the number with a leading zero (e.g., 01, 02)
            text = "${String.format("%02d", item.id)}. ${item.question}",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = Color.Black,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(6.dp)) // Small vertical gap

        // --- Answer/Body Text (Regular) ---
        Text(
            text = item.answer,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Gray, // Use a slightly subdued color for body text
            modifier = Modifier.fillMaxWidth()
        )
    }
}
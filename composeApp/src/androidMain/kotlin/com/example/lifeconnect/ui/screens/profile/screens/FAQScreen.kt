package com.example.lifeconnect.ui.screens.profile.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.lifeconnect.ui.screens.profile.components.DefaultAppBar
import com.example.lifeconnect.ui.screens.profile.components.FAQItem
import com.example.lifeconnect.ui.screens.profile.components.FAQItemModel
import org.jetbrains.compose.ui.tooling.preview.Preview


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FAQScreen(modifier: Modifier = Modifier, onBackClick: () -> Unit = {}) {
    // 1. Dummy Data populated from the image
    val faqItems = remember {
        listOf(
            FAQItemModel(
                1,
                "Sed ut perspiciatis unde omnis iste natus?",
                "eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit"
            ),
            FAQItemModel(2, "Sed ut perspiciatis unde iste natus?", "eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit"),
            FAQItemModel(3, "Sed ut perspiciatis unde iste natus?", "eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit"),
            FAQItemModel(4, "Sed ut perspiciatis unde iste natus?", "eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit"),
            FAQItemModel(5, "Sed ut perspiciatis unde iste natus?", "eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit"),
            FAQItemModel(6, "The final question is here?", "This is the sixth answer, demonstrating scrolling functionality and complete list structure."),
        )
    }

    Scaffold(
        topBar = {
            DefaultAppBar(
                title = "FAQ",
                onBackClick
            )
        },
        modifier = modifier.fillMaxSize()
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            items(items = faqItems, key = { it.id }) { item ->
                Column {
                    FAQItem(item = item)

                    // Separator line, matching the thin, light gray line in the design
                    HorizontalDivider(color = Color(0xFFE0E0E0), thickness = 1.dp)
                }

            }
        }
    }
}

// Example usage
@Preview(showBackground = true)
@Composable
fun PreviewFAQScreen() {
    MaterialTheme {
        FAQScreen()
    }
}
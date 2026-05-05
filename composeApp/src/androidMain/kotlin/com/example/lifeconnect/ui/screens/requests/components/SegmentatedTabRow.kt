package com.example.lifeconnect.ui.screens.requests.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import org.jetbrains.compose.ui.tooling.preview.Preview

// 2. The main tab component
@Preview(showBackground = true)
@Composable
fun SegmentedTabRow(
    tabTitles: List<String> = listOf("Requests", "Camps"),
    selectedTabIndex: Int = 0,
    onTabSelected: (Int) -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .height(48.dp)
            .background(
                color = Color(0xFFEFEFF4),
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        PrimaryTabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = Modifier.fillMaxSize(),

            // Explicitly set containerColor to Transparent to show the Row background
            containerColor = Color.Transparent,

            // Removes the default divider line
            divider = {},

            // The custom indicator is the key to the button-style appearance
            indicator = {
                val indicatorModifier = Modifier.tabIndicatorOffset(
                    selectedTabIndex = selectedTabIndex,
                    matchContentSize = false
                )
                Box(
                    modifier = indicatorModifier
                        .zIndex(-1f)
                        .fillMaxHeight()
                        .padding(4.dp)
                        .shadow(2.dp, RoundedCornerShape(12.dp))
                        .background(Color.White, RoundedCornerShape(12.dp))
                )
            }

        ) {
            tabTitles.forEachIndexed { index, title ->
                val isSelected = selectedTabIndex == index
                Tab(
                    selected = isSelected,
                    onClick = { onTabSelected(index) },
                    // Ensure the individual Tab background is transparent
                    modifier = Modifier.background(Color.Transparent).fillMaxWidth(),
                    text = {
                        Text(
                            text = title,
                            color = if (isSelected) Color.Black else Color.Gray,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                )
            }
        }
    }
}

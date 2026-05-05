package com.example.lifeconnect.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lifeconnect.ui.screens.home.components.AwarenessCarousel
import com.example.lifeconnect.ui.screens.home.components.BloodGroupGridView
import com.example.lifeconnect.ui.screens.home.components.ContributionGridView
import com.example.lifeconnect.ui.screens.home.components.HomeScreenHeader
import com.example.lifeconnect.ui.screens.home.components.RecentPost

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun HomeScreen(modifier: Modifier = Modifier){
        LazyColumn(
            modifier = modifier.padding(top = 120.dp),
            contentPadding = PaddingValues(vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            // Each section of your screen is now a separate `item
            item {
                AwarenessCarousel()
            }
            item {
                BloodGroupGridView()
            }
            item {
                ContributionGridView()
            }
            item {
                RecentPost()
            }
        }
}




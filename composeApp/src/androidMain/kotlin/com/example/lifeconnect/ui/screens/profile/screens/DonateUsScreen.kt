package com.example.lifeconnect.ui.screens.profile.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lifeconnect.ui.screens.profile.components.DefaultAppBar
import org.jetbrains.compose.ui.tooling.preview.Preview
import com.example.lifeconnect.R
import com.example.lifeconnect.ui.screens.profile.components.DonationCard
import com.example.lifeconnect.ui.screens.profile.components.HelperCard
import com.example.lifeconnect.ui.screens.profile.models.HelperModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DonateUsScreen(modifier: Modifier = Modifier, onBackClick: () -> Unit = {}) {
    val helpers = listOf(
        HelperModel(
            1,
            "Ronald Richards",
            "+88 123 456 79",
            painterResource(id = R.drawable.ic_launcher_background)
        ),
        HelperModel(2, "Theresa Webb", "+88 123 456 79", painterResource(id = R.drawable.ic_launcher_background)),
        HelperModel(3, "Kathryn Murphy", "+88 123 456 79", painterResource(id = R.drawable.ic_launcher_background)),
        HelperModel(4, "Marvin McKinney", "+88 123 456 79", painterResource(id = R.drawable.ic_launcher_background))
    )

    Scaffold(
        topBar = {
            DefaultAppBar(
                title = "Donate Us",
                onBackClick
            )
        },
    ) { paddingValues ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                DonationCard()
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "See who helped us",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    color = Color.Black,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }

            items(items = helpers, key = { it.id }) { helper ->
                HelperCard(helper = helper)
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDonateUsScreen() {
    MaterialTheme {
        DonateUsScreen()
    }
}
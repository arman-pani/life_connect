package com.example.lifeconnect.ui.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lifeconnect.ui.components.CustomFilledButton
import com.example.lifeconnect.R
import com.example.lifeconnect.ui.components.PageIndicator
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingScreen() {
    val pageCount = 3
    val pagerState = rememberPagerState (initialPage = 0) { pageCount }
    val scope = rememberCoroutineScope()

    val onboardingPages = listOf(
        OnboardingDetails(
            image = R.drawable.ic_launcher_background,
            title = "Donate Blood",
            subtitle = "Your single donation can save up to three lives. Become a hero today!"
        ),
        OnboardingDetails(
            image = R.drawable.ic_launcher_background,
            title = "Find Donors Nearby",
            subtitle = "Quickly locate nearby donors and connect instantly in times of need."
        ),
        OnboardingDetails(
            image = R.drawable.ic_launcher_background,
            title = "Stay Notified",
            subtitle = "Get real-time alerts for donation camps and emergency requirements."
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text("")},
                navigationIcon =
                    {
                        if (pagerState.currentPage > 0 ) {
                            IconButton(onClick = {
                                scope.launch {
                                    pagerState.animateScrollToPage(pagerState.currentPage - 1)
                                }
                            }) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Back"
                                )
                            }
                        }
                    },
                actions = {
                    TextButton(onClick = {
                        scope.launch { pagerState.scrollToPage(pageCount - 1) }
                    }) {
                        Text("Skip")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxWidth()
            ) { page ->
                val details = onboardingPages[page]
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ){
                    Image(
                        painter = painterResource(id = details.image),
                        contentDescription = "Example image",
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.size(15.dp))
                    Text(
                        text = details.title,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(modifier = Modifier.size(15.dp))
                    Text(
                        text= details.subtitle,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyMedium
                    )


                }
            }
            PageIndicator(pageCount, pagerState)

            CustomFilledButton(
                label = if (pagerState.currentPage < pageCount - 1) "Next" else "Get Started",
                onClick = {
                    scope.launch {
                        if (pagerState.currentPage < pageCount - 1) {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        } else {

                        }
                    }
                },
            )
        }


    }
}


@Preview
@Composable
fun OnboardingScreenPreview(){
    OnboardingScreen()
}

data class OnboardingDetails (
    val image: Int,
    val title: String,
    val subtitle: String
)
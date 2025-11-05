package com.example.lifeconnect.ui.screens.home.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.lifeconnect.R
import kotlinx.coroutines.delay

@Composable
fun AwarenessCarousel() {
    val pageCount = 3
    val pagerState = rememberPagerState (initialPage = 0) { pageCount }
    val scope = rememberCoroutineScope()

    val awarenessPages = listOf(
        AwarenessDetails(
            image = R.drawable.ic_launcher_background,
            title = "Donate Blood"
        ),
        AwarenessDetails(
            image = R.drawable.ic_launcher_background,
            title = "Find Donors\nNearby"
        ),
        AwarenessDetails(
            image = R.drawable.ic_launcher_background,
            title = "Stay Notified"
        )
    )

    LaunchedEffect(Unit) {
        while(true){
            delay(5000)
            val nextPage = (pagerState.currentPage + 1) % pageCount
            pagerState.animateScrollToPage(nextPage)
        }
    }


        Box(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .height(140.dp)
                .clip(shape = RoundedCornerShape(10.dp))
                .background(color = Color.Red.copy(alpha = 0.1f))
                .padding(horizontal = 20.dp, vertical = 20.dp)
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { index ->
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = awarenessPages[index].title,
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Image(
                        painter = painterResource(id = awarenessPages[index].image),
                        contentDescription = null,
                        modifier = Modifier.size(100.dp).padding(horizontal = 5.dp)
                    )
                }
            }
            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
            ) {
                DotIndicator(pagerState, pageCount)
            }
        }
}


data class AwarenessDetails(
    val title: String,
    val image: Int,
)

@Composable
fun DotIndicator(pageState: PagerState, pageCount: Int){
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        repeat(pageCount){ index ->
            val isSelected = pageState.currentPage == index

            val dotColor by animateColorAsState(
                targetValue = if (isSelected) Color.Red else Color.Gray,
                label = "dotColor"
            )

            Box(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .background(color = dotColor, shape = CircleShape)
                    .size(12.dp),

                )
        }
    }
}
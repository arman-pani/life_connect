package com.example.lifeconnect.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun PageIndicator(pageCount: Int, pageState: PagerState){
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pageCount){ index ->
            val isSelected = pageState.currentPage == index

            val dotWidth by animateDpAsState(
                targetValue = if (isSelected) 36.dp else 18.dp,
                label = "dotWidth"
            )

            val dotColor by animateColorAsState(
                targetValue = if (isSelected) Color.Red else Color.Gray,
                label = "dotColor"
            )

            Box(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .background(color = dotColor, shape = CircleShape)
                    .size(width = dotWidth, height = 18.dp),

                )
        }
    }
}
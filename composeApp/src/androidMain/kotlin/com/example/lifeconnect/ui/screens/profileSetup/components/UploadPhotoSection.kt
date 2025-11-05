package com.example.lifeconnect.ui.screens.profileSetup.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun UploadPhotoSection(){
    Column (
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ){
        Column (
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ){
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(color = Color.Red, shape = CircleShape)
                    .align(alignment = Alignment.CenterHorizontally),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = "photo",
                    tint = Color.White,
                    modifier = Modifier.size(40.dp)
                )
            }
            Text("Upload Your Photo", style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
        }

        Column (
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .drawBehind {
                        val stroke = Stroke(
                            width = 2.dp.toPx(),
                            pathEffect = PathEffect.dashPathEffect(
                                floatArrayOf(10f, 10f),
                                0f
                            ) // 10px dash, 10px gap
                        )
                        drawRoundRect(
                            color = Color.LightGray,
                            style = stroke,
                            cornerRadius = CornerRadius(12.dp.toPx()) // optional rounded corners
                        )
                    }
                    .padding(vertical = 40.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.AccountCircle,
                        contentDescription = "photo",
                        tint = Color.DarkGray,
                        modifier = Modifier.size(40.dp)
                    )
                    Text(
                        "Upload your profile image",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.DarkGray
                    )
                }
            }
            Text("Upload upto 1MB")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UploadPhotoSectionPreview(){
    UploadPhotoSection()
}
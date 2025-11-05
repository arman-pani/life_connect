package com.example.lifeconnect.ui.screens.requests.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.lifeconnect.ui.screens.requests.BloodRequestDetails


@Composable
fun RequestCard(modifier: Modifier = Modifier, requestDetails: BloodRequestDetails, onClick: () -> Unit){
    Card(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(width = 1.dp, color = Color.LightGray)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 15.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Image(
                painter = painterResource(id = requestDetails.bloodImage),
                contentDescription = "Blood Group",
                modifier = Modifier.size(50.dp)
            )

            Column (
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ){
                Text(
                    text = requestDetails.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Place,
                        modifier = modifier.size(16.dp),
                        contentDescription = "Location"
                    )
                    Text(
                        text = requestDetails.location,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        modifier = modifier.size(16.dp),
                        contentDescription = "Time"
                    )
                    Text(
                        text = requestDetails.time,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}
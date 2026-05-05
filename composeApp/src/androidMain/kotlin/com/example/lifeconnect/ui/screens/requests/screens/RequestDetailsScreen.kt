package com.example.lifeconnect.ui.screens.requests.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.lifeconnect.R
import com.example.lifeconnect.ui.requests.BloodRequestViewModel


@OptIn(ExperimentalMaterial3Api::class)
//@Preview()
@Composable
fun RequestDetailsScreen(
    viewModel: BloodRequestViewModel = viewModel(),
    navController: NavController
){
    val requestDetails = viewModel.selectedRequest.collectAsState().value

    val detailRows = listOf(
        Triple(Icons.Outlined.Person, "Contact Person", requestDetails?.personName ?: ""),
        Triple(Icons.Outlined.Call, "Phone Number", requestDetails?.contact ?: ""),
        Triple(Icons.Outlined.Info, "Bags Required", "${requestDetails?.bagsRequired ?: 0} bags"),
        Triple(Icons.Outlined.LocationOn, "Location", requestDetails?.location ?: ""),
        Triple(Icons.Outlined.LocationOn, "Time", requestDetails?.time ?: "")
    )
        Column (
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Image(
                painter = painterResource(
                    id = requestDetails?.bloodImage ?: R.drawable.ic_launcher_background
                ),
                contentDescription = "Blood Group",
                modifier = Modifier.size(80.dp)
            )
            Text(
                requestDetails?.title.toString(),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            FlowColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                detailRows.forEachIndexed { index, detailRow ->
                    Column(
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        if (index == 0) HorizontalDivider(color = Color.LightGray)
                        DetailRow(
                            icon = detailRow.first,
                            title = detailRow.second,
                            trailing = detailRow.third,
                        )
                        HorizontalDivider(color = Color.LightGray)
                    }
                }
            }
        }
}

@Composable
fun DetailRow(
    icon: ImageVector,
    title: String,
    trailing: String,
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Box(
            modifier = modifier
                .size(36.dp)
                .background(color = Color.Red.copy(alpha = 0.1f), shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                modifier = modifier.size(25.dp),
                tint = Color.Red,
                contentDescription = "Location"
            )
        }
        Text(title, style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = modifier.weight(1f))
        Text(trailing, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
    }
}
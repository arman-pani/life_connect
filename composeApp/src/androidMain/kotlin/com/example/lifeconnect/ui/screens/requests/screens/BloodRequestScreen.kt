package com.example.lifeconnect.ui.screens.requests.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lifeconnect.ui.navigation.RequestSubScreen
import com.example.lifeconnect.models.BloodRequestDetails
import com.example.lifeconnect.ui.screens.requests.components.RequestCard
import com.example.lifeconnect.ui.screens.requests.components.SegmentedTabRow
import com.example.lifeconnect.ui.requests.BloodRequestViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview


//@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun BloodRequestScreen(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController(),
    viewModel: BloodRequestViewModel = viewModel()
){
    val requests = viewModel.nearbyRequests.collectAsState().value

    val tabTitles = listOf("Requests", "Camps")
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.White,
                titleContentColor = Color.Black
            ),
            title = { Text("Blood Requests") },
            actions = {
                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Default.FilterAlt,
                        contentDescription = "Filter"
                    )
                }
            }
        )

        SegmentedTabRow(
            tabTitles = tabTitles,
            selectedTabIndex = selectedTabIndex,
            onTabSelected = { index -> selectedTabIndex = index }
        )

        when(selectedTabIndex){

            0 -> RequestsTab(
                requests = requests,
                onCardClick = { request ->
                    viewModel.selectRequest(request)
                    navController.navigate(RequestSubScreen.REQUEST_DETAILS.route)
                }
            )

            1 -> CampsTab()
        }
    }

}

@Composable
fun RequestsTab(
    modifier : Modifier = Modifier,
    requests : List<BloodRequestDetails>,
    onCardClick : (BloodRequestDetails) -> Unit,
){
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(requests) { request ->
            RequestCard(
                onClick = {onCardClick(request)},
                requestDetails = request
            )
        }
    }
}

@Composable
fun CampsTab(
    modifier : Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Upcoming Blood Donation Camps List", style = MaterialTheme.typography.headlineMedium)
    }
}

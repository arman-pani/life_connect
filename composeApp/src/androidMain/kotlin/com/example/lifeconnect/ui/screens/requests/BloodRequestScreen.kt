package com.example.lifeconnect.ui.screens.requests

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.lifeconnect.ui.navigation.IndependentDestination
import com.example.lifeconnect.ui.screens.requests.components.RequestCard


//@Preview(showBackground = true)
@Composable
fun BloodRequestScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: BloodRequestViewModel = viewModel()
){
    val requests = viewModel.nearbyRequests.collectAsState().value

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(requests) { request ->
            RequestCard(
                onClick = {
                    viewModel.selectRequest(request)
                    navController.navigate(IndependentDestination.BLOODREQUESTDETAILS.route)
                          },
                requestDetails = request)
        }
    }
}


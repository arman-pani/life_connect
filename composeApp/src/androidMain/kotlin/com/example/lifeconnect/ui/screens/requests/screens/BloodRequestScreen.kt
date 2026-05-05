package com.example.lifeconnect.ui.screens.requests

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.lifeconnect.ui.screens.requests.components.RequestCard
import com.example.lifeconnect.ui.screens.requests.viewModels.BloodRequestViewModel


//@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BloodRequestScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: BloodRequestViewModel = viewModel()
){
    val requests = viewModel.nearbyRequests.collectAsState().value
        LazyColumn(
            modifier = modifier
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(requests) { request ->
                RequestCard(
                    onClick = {
                        viewModel.selectRequest(request)
//                        navController.navigate(IndependentDestination.BLOODREQUESTDETAILS.route)
                    },
                    requestDetails = request)
            }
        }
}


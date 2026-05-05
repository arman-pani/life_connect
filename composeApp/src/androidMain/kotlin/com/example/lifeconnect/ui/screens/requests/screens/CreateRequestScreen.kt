package com.example.lifeconnect.ui.screens.requests.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lifeconnect.ui.components.CustomFilledButton
import com.example.lifeconnect.ui.components.InputDropdownField
import com.example.lifeconnect.ui.components.InputTextField
import com.example.lifeconnect.ui.navigation.RequestSubScreen
import com.example.lifeconnect.ui.requests.LocationResultData
import com.example.lifeconnect.ui.requests.CreateRequestViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Preview()
@Composable
fun CreateRequestScreen(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController(),
    viewModel: CreateRequestViewModel = viewModel(),
){
    // Collect state from the ViewModel
    val state by viewModel.uiState.collectAsState()
    val navBackStackEntry = navController.currentBackStackEntryAsState().value

    LaunchedEffect(navBackStackEntry) {
        val result = navBackStackEntry
            ?.savedStateHandle
            ?.get<LocationResultData>("location_result_key")

        if (result != null) {
            // Update the ViewModel with the result
            println("Received location: ${result.address}")
            viewModel.updateLocation(result)

            // Consume the key to prevent re-triggering on rotation/recomposition
            navBackStackEntry.savedStateHandle.remove<LocationResultData>("location_result_key")
        }
    }

    // Determine if the form is valid for submission
    val isFormValid = state.patientName.isNotBlank() &&
            state.bloodGroup.isNotBlank() &&
            state.contactNumber.isNotBlank() &&
            !state.isLoadingLocation // Wait until location is resolved

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {navController.popBackStack()}) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                title = { Text("Create Blood Request") },

            )
        } ,
    ) { innerPadding ->
        Column (
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ){
            InputTextField(
                label = "Patient Name",
                placeholder = "Enter name",
                value = state.patientName,
                onValueChange = viewModel::updatePatientName,

            )
            InputDropdownField(
                label = "Select Group",
                placeholder = "Blood Group"
            )
            InputTextField(
                label = "Contact Person Name",
                placeholder = "Enter name",
                value = state.contactPerson,
                onValueChange = viewModel::updateContactPerson
            )
            InputTextField(
                label = "Contact Number",
                placeholder = "Enter number",
                value = state.contactNumber,
                onValueChange = viewModel::updateContactNumber
            )
            InputTextField(
                label = "Location",
                readOnly = true,
                placeholder = "Enter location",
                value = state.locationAddress,
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "Person",
                        modifier = Modifier.clickable(onClick = {
                            if (!state.isLoadingLocation) {
                                // Navigate to the location picker screen
                                navController.navigate(RequestSubScreen.LOCATION_PICKER.route)
                            }
                        })
                    )
                }
            )

            InputTextField(
                label = "Hospital Name",
                placeholder = "Enter hospital",
                value = "",
                onValueChange = viewModel::updateHospitalName
            )

            Spacer(modifier = Modifier.weight(1f))
            CustomFilledButton(
                onClick = viewModel::submitRequest,
                label = "Request for Blood",
                enabled = isFormValid && !state.requestSuccessful
            )
        }

    }
}

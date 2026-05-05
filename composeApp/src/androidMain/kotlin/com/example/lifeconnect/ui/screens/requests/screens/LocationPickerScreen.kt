package com.example.lifeconnect.ui.screens.requests.screens

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lifeconnect.ui.screens.requests.components.LocationSearchSheetContent
import com.example.lifeconnect.ui.requests.LocationPickerViewModel
import com.example.lifeconnect.ui.requests.LocationPickerViewModelFactory
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun LocationPickerScreen(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController(), // Added NavController
    viewModel: LocationPickerViewModel = viewModel(factory = LocationPickerViewModelFactory(
        LocalContext.current)
    )
) {
    val state by viewModel.uiState.collectAsState()
    val scope = rememberCoroutineScope()

    // 🔑 Use rememberBottomSheetScaffoldState for the scaffold structure
    val sheetState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            skipHiddenState = false,
            initialValue = SheetValue.PartiallyExpanded // Start minimized
        )
    )

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(state.initialLatLng, 15f)
    }

    LaunchedEffect(state.isLoadingInitialLocation) {
        if (!state.isLoadingInitialLocation) {
            cameraPositionState.position = CameraPosition.fromLatLngZoom(state.initialLatLng, 15f)
        }
    }

    // Update ViewModel when the map view changes (i.e., when drag stops)
    LaunchedEffect(cameraPositionState.isMoving) {
        if (!cameraPositionState.isMoving && !state.isLoadingInitialLocation) {
            viewModel.updatePinLocation(cameraPositionState.position.target)
        }
    }

    // Handle map movement when a search result is confirmed (via ViewModel)
    LaunchedEffect(state.searchedAddress) {
        state.searchedAddress?.let {
            // Check if the LatLng in state has been updated by the ViewModel's Geocoding result
            if (state.selectedLatLng.latitude != 0.0 || state.selectedLatLng.longitude != 0.0) {
                scope.launch {
                    cameraPositionState.animate(
                        update = CameraUpdateFactory.newLatLngZoom(state.selectedLatLng, 15f),
                        durationMs = 1000
                    )
                    viewModel.clearSearchConfirmation()
                }
            }
        }
    }

    // --- Permissions and Properties Setup (omitted for brevity, assume correct) ---
    val context = LocalContext.current
    var permissionGranted by remember { mutableStateOf(hasLocationPermission(context)) }
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean -> permissionGranted = isGranted }

    LaunchedEffect(Unit) {
        if (!permissionGranted) {
            permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    val properties by remember {
        mutableStateOf(MapProperties(isMyLocationEnabled = permissionGranted))
    }


    BottomSheetScaffold(
        scaffoldState = sheetState,
        // The sheet content is now the persistent bottom bar
        sheetContent = {
            LocationSearchSheetContent(
                sheetState = sheetState.bottomSheetState, // Pass the inner SheetState
                state = state,
                onConfirm = { viewModel.createLocationResult(navController) },
                onSearchTextChange = viewModel::updateSearchText,
                onSearchConfirmed = viewModel::confirmSearch
            )
        },
        // Set properties for the sheet appearance
        sheetPeekHeight = 300.dp, // Defines the height when minimized
        sheetContainerColor = MaterialTheme.colorScheme.surface,
        sheetShadowElevation = 8.dp,
        // Optional: Hide the default dimming effect to keep the map clean
//        sheetScrimColor = Color.Transparent
    ) { innerPadding ->

        // 🔑 Body Content: This is the Map and the Static Pin
        Box(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            // Map fills the entire background
            GoogleMap(
                modifier = Modifier.matchParentSize(),
                cameraPositionState = cameraPositionState,
                properties = properties,
                uiSettings = MapUiSettings(scrollGesturesEnabled = !state.isLoadingInitialLocation)
                // Optional: When the sheet is opened, the user might expect the map to stop interaction
                // But generally, the map should remain active.
            )

            // 🔑 Centered Pin Icon (The Draggable Target)
            if (!state.isLoadingInitialLocation) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Destination Pin",
                    tint = Color.Red,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .offset(y = (-24).dp)
                        .size(48.dp)
                )
            } else {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}

private fun hasLocationPermission(context: Context): Boolean {
    return ContextCompat.checkSelfPermission(
        context,
        Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED
}
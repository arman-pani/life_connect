package com.example.lifeconnect.ui.screens.requests.viewModels

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Build
import android.os.Looper
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.lifeconnect.ui.screens.requests.models.LocationResultData
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import java.io.IOException
import java.util.Locale
import kotlin.coroutines.resume


data class LocationPickerState(
    val selectedLatLng: LatLng = LatLng(0.0, 0.0), // The current center of the map/pin
    val initialLatLng: LatLng = LatLng(0.0, 0.0), // The initial location (current user location)
    val searchText: String = "",
    val searchedAddress: String? = null,
    val isLoadingInitialLocation: Boolean = true,
    val currentAddress: String = "Finding your location...", // Address under the pin
    val addressLookupFailed: Boolean = false, // Track if geocoding/reverse geocoding failed
    val suggestions: List<String> = emptyList() // NEW: Location suggestions
)

// --- ViewModel Factory to pass Context to ViewModel ---
class LocationPickerViewModelFactory(private val context: Context) : androidx.lifecycle.ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LocationPickerViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LocationPickerViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


private fun hasLocationPermission(context: Context): Boolean {
    return ContextCompat.checkSelfPermission(
        context,
        Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED
}
/**
 * NEW: Uses FusedLocationProviderClient to get the current device location.
 * This is a suspend function that handles location requests and callbacks.
 */
@SuppressLint("MissingPermission") // Permission checked by caller/Composable scope
suspend fun getCurrentLocation(context: Context): LatLng? = suspendCancellableCoroutine { continuation ->
    if (!hasLocationPermission(context)) {
        continuation.resume(null)
        return@suspendCancellableCoroutine
    }

    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    // 1. Try to get the last known location first (quick check)
    fusedLocationClient.lastLocation.addOnSuccessListener { location ->
        if (location != null) {
            continuation.resume(LatLng(location.latitude, location.longitude))
            return@addOnSuccessListener
        }

        // 2. If last location is null, request a fresh location update
        val locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 1000)
            .setMaxUpdates(1) // Get only one update
            .build()

        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                fusedLocationClient.removeLocationUpdates(this) // Stop updates immediately

                val latestLocation = locationResult.lastLocation
                if (latestLocation != null) {
                    continuation.resume(LatLng(latestLocation.latitude, latestLocation.longitude))
                } else {
                    continuation.resume(null)
                }
            }
        }

        if (continuation.isCancelled) {
            fusedLocationClient.removeLocationUpdates(locationCallback)
            return@addOnSuccessListener
        }

        // Request updates on the main thread (Looper.getMainLooper())
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())
    }
        .addOnFailureListener {
            continuation.resume(null)
        }

    // Handle cancellation of the coroutine
    continuation.invokeOnCancellation {
        fusedLocationClient.removeLocationUpdates(object : LocationCallback() {}) // Empty callback to stop location requests cleanly
    }
}

class LocationPickerViewModel(context: Context) : ViewModel() {
    private val _uiState = MutableStateFlow(LocationPickerState())
    val uiState: StateFlow<LocationPickerState> = _uiState

    // Geocoder requires Context
    private val geocoder = Geocoder(context, Locale.getDefault())
    private val applicationContext = context.applicationContext

    // For debouncing search input
    private var searchJob: Job? = null

    init {
        // Fetch current GPS location (simulated)
        fetchCurrentLocation()
    }

    private fun fetchCurrentLocation() {
        viewModelScope.launch {
            val location = getCurrentLocation(applicationContext)

            val currentLatLng = location ?: LatLng(34.0522, -118.2437) // Fallback to Los Angeles

            val initialAddress = reverseGeocodeLatLng(currentLatLng)

            _uiState.update {
                it.copy(
                    selectedLatLng = currentLatLng,
                    initialLatLng = currentLatLng,
                    currentAddress = initialAddress,
                    isLoadingInitialLocation = false
                )
            }
        }
    }
    private suspend fun reverseGeocodeLatLng(latLng: LatLng): String {
        return withContext(Dispatchers.IO) {
            try {
                @Suppress("DEPRECATION")
                val addresses = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)
                } else {
                    geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)
                }

                val address = addresses?.firstOrNull()

                // Format the address cleanly (e.g., Street, City, Country)
                val addressLine = address?.getAddressLine(0) ?: "Unknown Location"

                // Note: We don't update the state here, we just return the string.
                // The caller (updatePinLocation or fetchCurrentLocation) updates the state.
                addressLine

            } catch (e: IOException) {
                // Handle network/service error
                println("Reverse Geocoding Error: ${e.message}")
                "Address Service Unavailable"
            }
        }
    }

    /**
     * Performs Geocoding to fetch a list of addresses based on a search query.
     */
    private suspend fun fetchLocationSuggestions(query: String) {
        if (query.isBlank()) {
            _uiState.update { it.copy(suggestions = emptyList()) }
            return
        }

        withContext(Dispatchers.IO) {
            try {
                @Suppress("DEPRECATION")
                val addresses = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    // Limiting results to 5 suggestions
                    geocoder.getFromLocationName(query, 5)
                } else {
                    geocoder.getFromLocationName(query, 5)
                }

                val suggestionsList = addresses
                    ?.mapNotNull { it.getAddressLine(0) } // Convert Address objects to formatted strings
                    ?: emptyList()

                _uiState.update { it.copy(suggestions = suggestionsList) }

            } catch (e: IOException) {
                println("Geocoding Suggestion Error: ${e.message}")
                _uiState.update { it.copy(suggestions = emptyList()) }
            }
        }
    }


    /**
     * Updates the UI state with the address corresponding to the new map pin location.
     */
    fun updatePinLocation(latLng: LatLng) = viewModelScope.launch {
        _uiState.update { it.copy(currentAddress = "Looking up address...", addressLookupFailed = false) }

        // Call the suspend function to get the string result
        val addressLine = reverseGeocodeLatLng(latLng)

        val failed = addressLine == "Address Service Unavailable" || addressLine == "Unknown Location"

        _uiState.update {
            it.copy(
                currentAddress = addressLine,
                selectedLatLng = latLng,
                addressLookupFailed = failed
            )
        }
    }

    /**
     * Converts a text search string into LatLng coordinates (Geocoding).
     */
    suspend fun getLatLngFromAddress(address: String): LatLng? {
        return withContext(Dispatchers.IO) {
            try {
                @Suppress("DEPRECATION")
                val addresses = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    geocoder.getFromLocationName(address, 1)
                } else {
                    geocoder.getFromLocationName(address, 1)
                }

                val location = addresses?.firstOrNull()

                if (location != null) {
                    // Update the search text to the formatted address if found
                    _uiState.update { it.copy(searchText = location.getAddressLine(0)) }
                    LatLng(location.latitude, location.longitude)
                } else {
                    null
                }

            } catch (e: IOException) {
                println("Geocoding Error: ${e.message}")
                null
            }
        }
    }


    fun updateSearchText(text: String) {
        _uiState.update { it.copy(searchText = text) }

        // Debounce search: Cancel the previous search job and launch a new one.
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(300) // Wait 300ms after the last keystroke
            fetchLocationSuggestions(text)
        }
    }

    fun confirmSearch() = viewModelScope.launch {
        val address = _uiState.value.searchText
        _uiState.update {
            it.copy(
                searchedAddress = address,
                suggestions = emptyList() // Clear suggestions upon search confirmation
            )
        }  // Trigger LaunchedEffect in Composable

        val newLatLng = getLatLngFromAddress(address)
        if (newLatLng != null) {
            // Update selectedLatLng to trigger map animation
            _uiState.update { it.copy(selectedLatLng = newLatLng) }
        } else {
            // Handle case where address was not found
            _uiState.update { it.copy(currentAddress = "Address Not Found", addressLookupFailed = true) }
        }
    }
    fun clearSearchConfirmation() {
        _uiState.update { it.copy(searchedAddress = null) }
    }

    // NOTE: This function is now updated to accept NavController for result passing
    fun createLocationResult(navController: NavController) {
        println("Button Pressed: createLocationResult")
        // Now using the real address and coordinates from state
        val finalLocation = _uiState.value.selectedLatLng
        val finalAddress = _uiState.value.currentAddress

        println("Final Address: $finalAddress, Final Location: $finalLocation")

        // 1. Create a bundle of data to pass back
        val resultKey = "location_result_key"
        navController.previousBackStackEntry
            ?.savedStateHandle
            ?.set(
                resultKey,
                LocationResultData(
                    address = finalAddress,
                    lat = finalLocation.latitude,
                    lng = finalLocation.longitude
                )
            )

        // 2. Pop the screen to return to CreateRequestScreen
        navController.popBackStack()
    }
}

// Data class for passing location result back (must be Parcelable or Serializable)

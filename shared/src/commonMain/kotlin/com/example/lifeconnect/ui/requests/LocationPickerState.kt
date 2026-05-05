package com.example.lifeconnect.ui.requests

data class LocationPickerState(
    val selectedLatLng: Pair<Double, Double> = Pair(0.0, 0.0), // The current center of the map/pin
    val initialLatLng: Pair<Double, Double> = Pair(0.0, 0.0), // The initial location (current user location)
    val searchText: String = "",
    val searchedAddress: String? = null,
    val isLoadingInitialLocation: Boolean = true,
    val currentAddress: String = "Finding your location...", // Address under the pin
    val addressLookupFailed: Boolean = false, // Track if geocoding/reverse geocoding failed
    val suggestions: List<String> = emptyList() // NEW: Location suggestions
)

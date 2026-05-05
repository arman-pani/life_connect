package com.example.lifeconnect.ui.requests

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lifeconnect.services.PlatformLocationService
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LocationPickerViewModel(
    private val locationService: PlatformLocationService
) : ViewModel() { // Use Voyager or KMP-ViewModel library
    private val _uiState = MutableStateFlow(LocationPickerState())
    val uiState: StateFlow<LocationPickerState> = _uiState

    private var searchJob: Job? = null

    init {
        fetchCurrentLocation()
    }

    private fun fetchCurrentLocation() {
        viewModelScope.launch {
            val location = locationService.getCurrentGPSLocation()
            val currentLatLng = location ?: Pair(34.0522, -118.2437)

            val initialAddress = locationService.reverseGeocode(currentLatLng.first, currentLatLng.second)

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

    fun updateSearchText(text: String) {
        _uiState.update { it.copy(searchText = text) }
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(300)
            val results = locationService.getSuggestions(text)
            _uiState.update { it.copy(suggestions = results) }
        }
    }

    fun updatePinLocation(lat: Double, lng: Double) = viewModelScope.launch {
        _uiState.update { it.copy(currentAddress = "Looking up address...", addressLookupFailed = false) }
        val addressLine = locationService.reverseGeocode(lat, lng)

        _uiState.update {
            it.copy(
                currentAddress = addressLine,
                selectedLatLng = Pair(lat, lng),
                addressLookupFailed = addressLine.contains("Unavailable")
            )
        }
    }

    fun confirmSearch() = viewModelScope.launch {
        val address = _uiState.value.searchText
        _uiState.update { it.copy(searchedAddress = address, suggestions = emptyList()) }

        val newLatLng = locationService.getLatLngFromAddress(address)
        if (newLatLng != null) {
            _uiState.update { it.copy(selectedLatLng = newLatLng) }
        } else {
            _uiState.update { it.copy(currentAddress = "Address Not Found", addressLookupFailed = true) }
        }
    }
}
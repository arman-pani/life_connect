package com.example.lifeconnect.ui.requests

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CreateRequestViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CreateRequestData())
    val uiState: StateFlow<CreateRequestData> = _uiState

    init {
        // Start fetching current location when ViewModel is created
        fetchCurrentLocation()
    }

    private fun fetchCurrentLocation() {
        // Simulate an asynchronous API call to get the device's location
        viewModelScope.launch {
            // Delay to simulate network/GPS lookup time
            delay(1500)

            // Simulate the successful retrieval of coordinates and address
            _uiState.update { currentState ->
                currentState.copy(
                    locationAddress = "123 Sample St, Default City", // Simulated geocoded address
                    locationLat = 34.0522, // Simulated latitude
                    locationLng = -118.2437, // Simulated longitude
                    isLoadingLocation = false
                )
            }
        }
    }

    // --- State Update Functions for Form Fields ---

    fun updatePatientName(name: String) {
        _uiState.update { it.copy(patientName = name) }
    }

    fun updateBloodGroup(group: String) {
        _uiState.update { it.copy(bloodGroup = group) }
    }

    fun updateContactPerson(name: String) {
        _uiState.update { it.copy(contactPerson = name) }
    }

    fun updateContactNumber(number: String) {
        _uiState.update { it.copy(contactNumber = number) }
    }

    fun updateHospitalName(hospital: String) {
        _uiState.update { it.copy(hospitalName = hospital) }
    }

    /**
     * Called when the user returns from the Location Picker screen.
     * @param address The formatted address string.
     * @param lat The selected latitude.
     * @param lng The selected longitude.
     */
    fun updateLocation(result: LocationResultData) {
        _uiState.update { currentState ->
            currentState.copy(
                locationAddress = result.address,
                locationLat = result.lat,
                locationLng = result.lng,
                isLoadingLocation = false
            )
        }
    }

    // --- Request Submission Logic ---

    fun submitRequest() {
        val data = _uiState.value
        println("Submitting Request: $data")

        // Perform validation here (e.g., check for empty fields)

        if (data.locationLat == null || data.locationLng == null) {
            println("Error: Location is not set.")
            return
        }

        viewModelScope.launch {
            // Simulate API call to submit the request
            delay(1000)

            // After successful submission:
            _uiState.update { it.copy(requestSuccessful = true) }
        }
    }
}

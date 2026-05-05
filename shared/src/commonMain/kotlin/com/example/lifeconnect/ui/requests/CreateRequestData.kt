package com.example.lifeconnect.ui.requests

data class CreateRequestData(
    val patientName: String = "",
    val bloodGroup: String = "",
    val contactPerson: String = "",
    val contactNumber: String = "",
    val locationAddress: String = "Loading current location...",
    val locationLat: Double? = null,
    val locationLng: Double? = null,
    val hospitalName: String = "",
    val isLoadingLocation: Boolean = true,
    val requestSuccessful: Boolean = false
)
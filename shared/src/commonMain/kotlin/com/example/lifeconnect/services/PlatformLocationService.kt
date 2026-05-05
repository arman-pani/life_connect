package com.example.lifeconnect.services

interface PlatformLocationService {
    suspend fun getCurrentGPSLocation(): Pair<Double, Double>?
    suspend fun reverseGeocode(lat: Double, lng: Double): String
    suspend fun getSuggestions(query: String): List<String>
    suspend fun getLatLngFromAddress(address: String): Pair<Double, Double>?
}
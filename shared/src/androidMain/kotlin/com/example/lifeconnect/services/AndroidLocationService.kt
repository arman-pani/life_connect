package com.example.lifeconnect.services

import android.annotation.SuppressLint
import android.content.Context
import android.location.Geocoder
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import java.util.Locale
import kotlin.coroutines.resume

class AndroidLocationService(private val context: Context) : PlatformLocationService {
    private val geocoder = Geocoder(context, Locale.getDefault())
    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission")
    override suspend fun getCurrentGPSLocation(): Pair<Double, Double>? =
        suspendCancellableCoroutine { cont ->
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                if (location != null) {
                    cont.resume(Pair(location.latitude, location.longitude))
                } else {
                    // If lastLocation is null, you'd trigger a fresh request here as in your original code
                    cont.resume(null)
                }
            }.addOnFailureListener { cont.resume(null) }
        }

    override suspend fun reverseGeocode(lat: Double, lng: Double): String =
        withContext(Dispatchers.IO) {
            try {
                val addresses = geocoder.getFromLocation(lat, lng, 1)
                addresses?.firstOrNull()?.getAddressLine(0) ?: "Unknown Location"
            } catch (e: Exception) {
                "Address Service Unavailable"
            }
        }

    override suspend fun getSuggestions(query: String): List<String> = withContext(Dispatchers.IO) {
        if (query.isBlank()) return@withContext emptyList()
        try {
            val addresses = geocoder.getFromLocationName(query, 5)
            addresses?.mapNotNull { it.getAddressLine(0) } ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getLatLngFromAddress(address: String): Pair<Double, Double>? =
        withContext(Dispatchers.IO) {
            try {
                val location = geocoder.getFromLocationName(address, 1)?.firstOrNull()
                location?.let { Pair(it.latitude, it.longitude) }
            } catch (e: Exception) {
                null
            }
        }
}
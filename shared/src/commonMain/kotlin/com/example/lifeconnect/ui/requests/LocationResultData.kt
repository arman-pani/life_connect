package com.example.lifeconnect.ui.requests

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocationResultData(
    val address: String,
    val lat: Double,
    val lng: Double
) : Parcelable

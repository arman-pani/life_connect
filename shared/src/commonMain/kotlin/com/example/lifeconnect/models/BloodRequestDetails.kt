package com.example.lifeconnect.models

data class BloodRequestDetails(
    val bloodImage: Int,
    val title: String,
    val personName: String,
    val contact: String,
    val location: String,
    val time: String,
    val bagsRequired: Int,
)
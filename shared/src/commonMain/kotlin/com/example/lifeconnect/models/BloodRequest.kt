package com.example.lifeconnect.models

import kotlinx.serialization.Serializable

@Serializable
data class BloodRequest(
    val id: String,
    val requesterId: String,
    val bloodGroup: String,
    val bagsRequired: Int,
    val contactName: String,
    val contactPhone: String,
    val location: String,
    val urgent: Boolean,
    val requestedAt: Long
)

@Serializable
data class CreateBloodRequestPayload(
    val bloodGroup: String,
    val bagsRequired: Int,
    val contactName: String,
    val contactPhone: String,
    val location: String,
    val urgent: Boolean = false // Default to false
)
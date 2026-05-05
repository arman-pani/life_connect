package com.example.lifeconnect.data

import com.example.lifeconnect.models.BloodRequest
import com.example.lifeconnect.models.CreateBloodRequestPayload

interface LifeConnectApi {
    suspend fun fetchBloodRequests(): List<BloodRequest>
    suspend fun createBloodRequest(request: CreateBloodRequestPayload)
}
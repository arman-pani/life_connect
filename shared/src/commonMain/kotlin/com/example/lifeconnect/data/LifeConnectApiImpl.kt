package com.example.lifeconnect.data

import com.example.lifeconnect.models.BloodRequest
import com.example.lifeconnect.models.CreateBloodRequestPayload
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.URLBuilder
import io.ktor.http.path

class LifeConnectApiImpl(private val httpClient: HttpClient) : LifeConnectApi {
    private val BASE_URL = ""

    override suspend fun createBloodRequest(request: CreateBloodRequestPayload) {
        try {
            val url = URLBuilder(BASE_URL).apply {
                path("create-request")
            }.build()

            val response = httpClient.post(url) {
                setBody(request)
            }

            if (response.status.value != 201 && response.status.value != 200) {
                println("Failed to create request. Status: ${response.status.value}, Body: ${response.body<String>()}")
            } else {
                println("Blood request successfully created.")
            }

        } catch (e: Exception){
            println("Error creating blood request: ${e.message}")
        }
    }

    override suspend fun fetchBloodRequests(): List<BloodRequest> {
        return try {
            val url = URLBuilder(BASE_URL).apply {
                path("blood-requests")
            }.build()

            httpClient.get(url).body()
        } catch (e: Exception){
            println("Error fetching blood requests: ${e.message}")
            emptyList()
        }
    }
}
package com.example.lifeconnect.ui.requests

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lifeconnect.data.dummyBloodRequests
import com.example.lifeconnect.models.BloodRequestDetails
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BloodRequestViewModel : ViewModel(){
    private var _nearbyRequests = MutableStateFlow<List<BloodRequestDetails>>(emptyList())
    val nearbyRequests: StateFlow<List<BloodRequestDetails>> = _nearbyRequests

    private var _selectedRequest = MutableStateFlow<BloodRequestDetails?>(null)
    val selectedRequest: StateFlow<BloodRequestDetails?> = _selectedRequest

    init {
        fetchNearbyRequests()
    }

    private fun fetchNearbyRequests() {
        viewModelScope.launch {
            _nearbyRequests.value = dummyBloodRequests
        }
    }

    fun selectRequest(request: BloodRequestDetails){
        _selectedRequest.value = request
    }

    fun clearSelectedRequest(){
        _selectedRequest.value = null
    }
}
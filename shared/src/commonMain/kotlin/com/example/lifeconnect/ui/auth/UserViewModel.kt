package com.example.lifeconnect.ui.auth


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lifeconnect.ImageStorageUtils
import com.example.lifeconnect.data.local.entity.UserEntity
import com.example.lifeconnect.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Clock.System
import kotlin.time.ExperimentalTime

enum class ProfileStep {
    PERSONAL_INFO,
    BASIC_INFO,
    PHOTO
}

class UserViewModel(
    private val imageUtils: ImageStorageUtils,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _user = MutableStateFlow<UserEntity?>(null)
    val user: StateFlow<UserEntity?> = _user

    private val _formState = MutableStateFlow(ProfileFormState())
    val formState: StateFlow<ProfileFormState> = _formState

    init {
        loadUser()
    }

    private fun loadUser() {
        viewModelScope.launch {
            _user.value = userRepository.getUser()
        }
    }

    fun isStepValid(step: ProfileStep): Boolean {
        val state = formState.value
        return when (step) {
            ProfileStep.PERSONAL_INFO ->
                state.name.isNotBlank() &&
                        state.bloodGroup.isNotBlank()

            ProfileStep.BASIC_INFO ->
                        state.gender.isNotBlank()

            ProfileStep.PHOTO ->
                true
        }
    }

    @OptIn(ExperimentalTime::class)
    fun handlePickedImage(bytes: ByteArray?) {
        if (bytes == null) return

        // 1. Generate a unique name (e.g., timestamp)
        val fileName = "profile_${System.now()}.jpg"

        // 2. Save to disk
        val absolutePath = imageUtils.saveImage(bytes, fileName)

        // 3. Store the PATH (or filename) in Database
        updateForm { copy(profilePhoto = absolutePath) }
    }

    fun updateForm(update: ProfileFormState.() -> ProfileFormState) {
        _formState.update { it.update() }
    }

    fun saveMobileNumber(mobile: String) {
        viewModelScope.launch {
            userRepository.saveMobileNumber(mobile)
            loadUser()
        }
    }

    fun saveProfile() {
        viewModelScope.launch {
            try {
                userRepository.saveProfile(
                    name = formState.value.name,
                    email = formState.value.email,
                    dob = formState.value.dob,
                    gender = formState.value.gender,
                    bloodGroup = formState.value.bloodGroup,
                    country = formState.value.country,
                    city = formState.value.city,
                    about = formState.value.about,
                    profilePhoto = formState.value.profilePhoto,
                    isDonater = false,
                )
                loadUser()
            } catch (e : Exception){
                println("Error saving profile")
            }

        }
    }

}
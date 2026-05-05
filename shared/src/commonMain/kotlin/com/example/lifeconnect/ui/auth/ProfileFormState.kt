package com.example.lifeconnect.ui.auth

import kotlinx.datetime.LocalDate

data class ProfileFormState(
    val name: String = "",
    val email: String = "",
    val dob: LocalDate = LocalDate(2000, 1, 1),
    val gender: String = "",
    val bloodGroup: String = "",
    val country: String = "",
    val city: String = "",
    val isDonater: Boolean = false,
    val about: String = "",
    val profilePhoto: String = ""
)
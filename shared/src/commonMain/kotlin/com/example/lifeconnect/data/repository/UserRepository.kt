package com.example.lifeconnect.data.repository

import com.example.lifeconnect.data.local.dao.UserDAO
import com.example.lifeconnect.data.local.entity.UserEntity
import io.ktor.util.logging.Logger
import kotlinx.datetime.LocalDate

class UserRepository(
    private val userDao: UserDAO
) {
    suspend fun saveMobileNumber(mobile: String) {
        userDao.insertUser(
            UserEntity(
                id = 0,
                mobileNumber = mobile,
            )
        )
    }

    suspend fun saveProfile(
        name: String,
        email: String,
        dob: LocalDate,
        gender: String,
        bloodGroup: String,
        country: String,
        city: String,
        isDonater: Boolean,
        about: String,
        profilePhoto: String
    ) {
        println("Saving started")
        userDao.updateProfile(
            name = name,
            email = email,
            country = country,
            city = city,
            dob = dob,
            gender = gender,
            about = about,
            isDonater = isDonater,
            bloodGroup = bloodGroup,
            profilePhoto = profilePhoto,
        )

        val user = userDao.getUser()

        println("Profile saved successfully: ${user?.name}")
    }

    suspend fun getUser(): UserEntity? = userDao.getUser()
}
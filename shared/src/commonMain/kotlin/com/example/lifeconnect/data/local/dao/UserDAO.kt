package com.example.lifeconnect.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lifeconnect.data.local.entity.UserEntity
import kotlinx.datetime.LocalDate

@Dao
interface UserDAO {
    @Query("SELECT * FROM user WHERE id = 0")
    suspend fun getUser(): UserEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Query("""
        UPDATE user 
        SET name = :name,
            email = :email,
            country = :country,
            city = :city,
            dob = :dob,
            gender = :gender,
            bloodGroup = :bloodGroup,
            about = :about,
            isDonater = :isDonater,
            profilePhoto = :profilePhoto,
            isProfileCompleted = 1
        WHERE id = 0
    """)
    suspend fun updateProfile(
        name: String,
        email: String,
        country: String,
        city: String,
        dob: LocalDate,
        gender: String,
        bloodGroup: String,
        about: String,
        isDonater: Boolean,
        profilePhoto: String,
    )
}
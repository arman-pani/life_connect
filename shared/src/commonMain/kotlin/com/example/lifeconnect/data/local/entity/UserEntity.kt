package com.example.lifeconnect.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.LocalDate
import androidx.room.TypeConverter

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey val id: Int = 0,
    val mobileNumber: String,
    val name: String? = null,
    val email: String? = null,
    val bloodGroup: String? = null,
    val city: String? = null,
    val country: String? = null,
    val dob: LocalDate? = null,
    val age: String? = null,
    val gender: String? = null,
    val about: String? = null,
    val profilePhoto: String? = null,
    val isDonater: Boolean = false,
    val isProfileCompleted: Boolean = false,
)



class DateConverter {
    @TypeConverter
    fun fromLocalDate(value: LocalDate?): String? {
        return value?.toString() // Saves as "YYYY-MM-DD"
    }

    @TypeConverter
    fun toLocalDate(value: String?): LocalDate? {
        return value?.let { LocalDate.parse(it) }
    }
}
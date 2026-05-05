package com.example.lifeconnect.utils

import kotlinx.datetime.LocalDate

// Convert LocalDate to String
fun localDateToString(date: LocalDate?): String {
    return date?.let {
        val month = it.monthNumber.toString().padStart(2, '0')
        val day = it.dayOfMonth.toString().padStart(2, '0')
        "$month/$day/${it.year}"
    } ?: ""
}

// Convert String back to LocalDate
fun stringToLocalDate(dateString: String): LocalDate? {
    if (dateString.isBlank()) {
        return null
    }
    // Try to parse the custom format "MM/dd/yyyy" first
    try {
        val parts = dateString.split('/')
        if (parts.size == 3) {
            val month = parts[0].toInt()
            val day = parts[1].toInt()
            val year = parts[2].toInt()
            return LocalDate(year, month, day)
        }
    } catch (e: Exception) {
        // Fall through to try the next format
    }

    // If custom parse fails, try the default ISO format "YYYY-MM-DD"
    try {
        return LocalDate.parse(dateString)
    } catch (e: Exception) {
        // If all parsing fails, return null to prevent a crash
        return null
    }
}
package com.example.lifeconnect

interface ImageStorageUtils {
    fun saveImage(bytes: ByteArray, fileName: String): String
}

package com.example.lifeconnect

// In iosMain
import platform.Foundation.*

actual class LocalFileWriter {
    actual fun saveImage(bytes: ByteArray, fileName: String): String {
        val paths = NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, true)
        val documentsDirectory = paths.first() as String
        val filePath = "$documentsDirectory/$fileName"

//        val data = bytes.toNSData() // Helper to convert ByteArray to NSData
//        data.writeToFile(filePath, true)
        return filePath
    }
}
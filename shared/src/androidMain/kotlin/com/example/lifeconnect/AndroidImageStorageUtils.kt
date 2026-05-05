package com.example.lifeconnect

import android.content.Context
import java.io.File

class AndroidImageStorageUtils(private val context: Context) : ImageStorageUtils {
    override fun saveImage(bytes: ByteArray, fileName: String): String {
        val file = File(context.filesDir, fileName)
        file.writeBytes(bytes)
        return file.absolutePath
    }
}
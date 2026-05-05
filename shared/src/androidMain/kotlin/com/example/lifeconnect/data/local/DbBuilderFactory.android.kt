package com.example.lifeconnect.data.local

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.lifeconnect.AndroidImageStorageUtils
import com.example.lifeconnect.ImageStorageUtils
import org.koin.dsl.module

actual class DbBuilderFactory(private val context: Context) {
    actual fun create(): RoomDatabase.Builder<AppDatabase> {
        val dbFile = context.getDatabasePath("lifeConnect.db")
        return Room.databaseBuilder<AppDatabase>(
            context = context,
            name = dbFile.absolutePath
        )
    }
}


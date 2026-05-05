package com.example.lifeconnect.data.local

import androidx.room.Room
import androidx.room.RoomDatabase
import org.koin.dsl.module
import platform.Foundation.NSHomeDirectory

actual class DbBuilderFactory {
    actual fun create(): RoomDatabase.Builder<AppDatabase> {
        val dbFilePath = NSHomeDirectory() + "/lifeConnect.db"
        return Room.databaseBuilder<AppDatabase>(
            name = dbFilePath,
            factory = { instantiateImpl() } // Required for iOS
        )
    }
}

fun instantiateImpl(): AppDatabase {
    throw NotImplementedError("The Room compiler should override this.")
}


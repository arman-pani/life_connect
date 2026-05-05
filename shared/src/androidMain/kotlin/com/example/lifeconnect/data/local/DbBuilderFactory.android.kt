package com.example.lifeconnect.data.local

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

// We use a helper function that takes context, which Koin will call
actual fun getDatabaseBuilder(context: Any): RoomDatabase.Builder<AppDatabase> {
    val appContext = context as Context
    val dbFile = appContext.getDatabasePath("lifeconnect.db")
    return Room.databaseBuilder<AppDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}
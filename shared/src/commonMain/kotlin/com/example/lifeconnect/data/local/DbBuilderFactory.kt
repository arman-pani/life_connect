package com.example.lifeconnect.data.local

import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver

expect class DbBuilderFactory {
    fun create(): RoomDatabase.Builder<AppDatabase>
}

fun getDatabase(builder: RoomDatabase.Builder<AppDatabase>): AppDatabase {
    return builder
        .fallbackToDestructiveMigration(dropAllTables = true)
        .setDriver(BundledSQLiteDriver()) // Use Bundled SQLite for KMP
        .build()
}
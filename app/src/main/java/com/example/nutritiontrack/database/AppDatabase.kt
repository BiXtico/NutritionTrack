package com.example.nutritiontrack.database

import android.content.Context
import android.provider.ContactsContract
import androidx.room.RoomDatabase
import androidx.room.Database
import androidx.room.Room

@Database(entities = [DatabaseMeal::class,DatabaseUser::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val dao: DAO
}

@Volatile
private lateinit var instance: AppDatabase

fun getInstance(context: Context): AppDatabase {
    synchronized(AppDatabase::class.java) {
        if (!::instance.isInitialized) {
            instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "local_database"
            ).fallbackToDestructiveMigration().build()
        }
        return instance
    }
}


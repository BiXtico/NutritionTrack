package com.example.nutritiontrack.database

import android.content.Context
import android.provider.ContactsContract
import androidx.room.RoomDatabase
import androidx.room.Database
import androidx.room.Room

@Database(entities = [Meal::class,User::class], version = 1,exportSchema = false)
abstract class AppDatabase:RoomDatabase(){
    abstract val dao: DAO

    companion object{
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context) :AppDatabase{
            synchronized(this){
                var _instance = instance
                if(_instance == null){
                    _instance = Room.databaseBuilder(context.applicationContext,
                    AppDatabase::class.java,
                    "local_database").fallbackToDestructiveMigration().build()
                }
                return _instance
            }
        }
    }

}

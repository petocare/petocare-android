package com.petocare.infra.database.local

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.petocare.infra.database.dao.UserDetailsDao
import com.petocare.infra.database.model.UserDetailsModel

@Database(entities = [UserDetailsModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private var instance: AppDatabase? = null
        fun getInstance(context: Application): AppDatabase? {
            if (instance == null) {
                synchronized(AppDatabase::class.java) {
                    if (instance == null) {
                        instance = Room.databaseBuilder(
                            context,
                            AppDatabase::class.java, "petocare_db"
                        ).fallbackToDestructiveMigration().build()
                    }
                }
            }
            return instance
        }
    }

    abstract fun userDetailsDao(): UserDetailsDao
}
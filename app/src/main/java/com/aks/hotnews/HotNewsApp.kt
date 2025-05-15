package com.aks.hotnews

import android.app.Application
import androidx.room.Room
import com.aks.hotnews.data.db.AppDatabase

class HotNewsApp : Application() {
    lateinit var database: AppDatabase
        private set

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
                this,
                AppDatabase::class.java,
                "hotnews-db"
            ).fallbackToDestructiveMigration(false)
            .build()
    }
}

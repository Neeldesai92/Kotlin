package com.neel.desai.workdemo.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.neel.desai.workdemo.TaskDao
import com.neel.desai.workdemo.model.Results

@Database(entities = [Results::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}
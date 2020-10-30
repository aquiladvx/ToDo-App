package com.debcomp.aql.debcentral.infra.base

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.debcomp.aql.debcentral.infra.TASK_TABLE
import com.debcomp.aql.debcentral.infra.data.entity.TaskDTO
import com.debcomp.aql.debcentral.infra.data.local.TaskDAO

@Database(entities = [TaskDTO::class], version = 1, exportSchema = false)
abstract class AppDataBase: RoomDatabase(){

    abstract fun taskDao(): TaskDAO

    companion object {
        @Volatile
        private var instance: AppDataBase? = null

        fun getDataBase(context: Context): AppDataBase =
            instance?: synchronized(this) {
                instance?: buildDataBase(context).also {
                    instance = it
                }
            }

        private fun buildDataBase(context: Context): AppDataBase =
            Room.databaseBuilder(context, AppDataBase::class.java, TASK_TABLE)
                .fallbackToDestructiveMigration()
                .build()
    }

}
package com.debcomp.aql.debcentral.infra.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.debcomp.aql.debcentral.infra.data.entity.TaskDTO

@Dao
interface TaskDAO {

    @Query("SELECT * FROM task_table ORDER BY name ASC")
    fun getAllTasks(): LiveData<List<TaskDTO>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(taskDTO: TaskDTO)
}
package com.debcomp.aql.debcentral.infra.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.debcomp.aql.debcentral.infra.data.entity.TaskDTO

@Dao
interface TaskDAO {

    @Query("SELECT * FROM task_table ORDER BY name ASC")
    fun getAllTasks(): LiveData<List<TaskDTO>>

    @Query("SELECT * FROM task_table WHERE id = :id")
    fun getTaskById(id: Int): LiveData<TaskDTO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTask(taskDTO: TaskDTO)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateTask(taskDTO: TaskDTO)

    @Query("DELETE FROM task_table")
    fun deleteAllTasks()

    @Query("DELETE FROM task_table WHERE id = :id")
    fun deleteTaskById(id: Int)


}
package com.debcomp.aql.debcentral.infra.data

import androidx.lifecycle.LiveData
import com.debcomp.aql.debcentral.infra.data.entity.TaskDTO
import com.debcomp.aql.debcentral.infra.data.local.TaskDAO

class TaskRepository private constructor(
    private val localDataSource: TaskDAO
){

    suspend fun addTask(task: TaskDTO) {
        localDataSource.insertTask(task)
    }

    fun getAllTasks(): LiveData<List<TaskDTO>> = localDataSource.getAllTasks()

    companion object {
        fun create(localDataSource: TaskDAO): TaskRepository = TaskRepository(localDataSource)
    }
}
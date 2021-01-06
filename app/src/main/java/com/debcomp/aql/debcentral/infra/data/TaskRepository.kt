package com.debcomp.aql.debcentral.infra.data

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.debcomp.aql.debcentral.infra.data.entity.TaskDTO
import com.debcomp.aql.debcentral.infra.data.local.TaskDAO

class TaskRepository private constructor(
    private val localDataSource: TaskDAO
){

    @WorkerThread
    fun addTask(task: TaskDTO) {
        localDataSource.insertTask(task)
    }

    fun getAllTasks(): LiveData<List<TaskDTO>> = localDataSource.getAllTasks()

    fun getTaskById(id: Int): LiveData<TaskDTO> = localDataSource.getTaskById(id)

    companion object {
        fun create(localDataSource: TaskDAO): TaskRepository = TaskRepository(localDataSource)
    }
}
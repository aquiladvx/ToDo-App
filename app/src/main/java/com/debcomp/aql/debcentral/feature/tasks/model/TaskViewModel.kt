package com.debcomp.aql.debcentral.feature.tasks.model

import android.app.Application
import androidx.lifecycle.*
import com.debcomp.aql.debcentral.infra.base.AppDataBase
import com.debcomp.aql.debcentral.infra.data.TaskRepository
import com.debcomp.aql.debcentral.infra.data.entity.TaskDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TaskRepository
    val allTasks: LiveData<List<TaskDTO>>

    init {
        val dao = AppDataBase.getDataBase(application).taskDao()
        repository = TaskRepository.create(dao)
        allTasks = repository.getAllTasks()
    }

    fun addTask(taskDTO: TaskDTO) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTask(taskDTO)
        }
    }

    class TaskViewModelFactory constructor(private val application: Application) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
                TaskViewModel(this.application) as T
            } else {
                throw IllegalArgumentException("ViewModel not found")
            }
        }
    }
}
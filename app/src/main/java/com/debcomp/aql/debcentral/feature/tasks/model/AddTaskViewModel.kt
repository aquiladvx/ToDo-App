package com.debcomp.aql.debcentral.feature.tasks.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.debcomp.aql.debcentral.infra.base.AppDataBase
import com.debcomp.aql.debcentral.infra.data.TaskRepository
import com.debcomp.aql.debcentral.infra.data.entity.TaskDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class AddTaskViewModel(application: Application): AndroidViewModel(application){

    private val repository: TaskRepository

    init {
        val dao = AppDataBase.getDataBase(application).taskDao()
        repository = TaskRepository.create(dao)
    }

    fun addTask(taskDTO: TaskDTO) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTask(taskDTO)
        }
    }

    class AddTaskViewModelFactory constructor(private val application: Application) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return if (modelClass.isAssignableFrom(AddTaskViewModel::class.java)) {
                AddTaskViewModel(this.application) as T
            } else {
                throw IllegalArgumentException("ViewModel not found")
            }
        }
    }
}
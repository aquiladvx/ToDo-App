package com.debcomp.aql.debcentral.feature.tasks.model

import android.app.Application
import androidx.lifecycle.*
import com.debcomp.aql.debcentral.infra.base.AppDataBase
import com.debcomp.aql.debcentral.infra.data.TaskRepository
import com.debcomp.aql.debcentral.infra.data.entity.TaskDTO
import java.lang.IllegalArgumentException

class DetailTaskViewModel(application: Application): AndroidViewModel(application) {

    private lateinit var repository: TaskRepository

    private val _id = MutableLiveData<Long>()
    private var _task: LiveData<TaskDTO> = _id
        .switchMap {
            repository.getTaskById(it.toInt())
        }

    val task: LiveData<TaskDTO> = _task

    init {
        val dao = AppDataBase.getDataBase(application).taskDao()
        repository = TaskRepository.create(dao)
    }

    fun start(id: Long) {
        _id.value = id
    }


    class DetailTaskViewModelFactory constructor(private val application: Application) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return if (modelClass.isAssignableFrom(DetailTaskViewModel::class.java)) {
                DetailTaskViewModel(this.application) as T
            } else {
                throw IllegalArgumentException("ViewModel not found")
            }
        }
    }
}
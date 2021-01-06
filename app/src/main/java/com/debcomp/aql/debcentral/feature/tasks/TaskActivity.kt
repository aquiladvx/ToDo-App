package com.debcomp.aql.debcentral.feature.tasks

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.debcomp.aql.debcentral.DEBCentralApplication
import com.debcomp.aql.debcentral.R
import com.debcomp.aql.debcentral.feature.tasks.model.TaskViewModel
import com.debcomp.aql.debcentral.infra.BaseActivity
import kotlinx.android.synthetic.main.activity_task_list.*

class TaskActivity: BaseActivity() {

    private lateinit var adapter: TaskAdapter
    private lateinit var viewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_task_list)

        adapter = TaskAdapter(
            this,
            ::taskClickListener
        )

        rv_all_tasks.adapter = adapter

        viewModel = ViewModelProvider(
            this,
            TaskViewModel.TaskViewModelFactory(DEBCentralApplication.instance),
        ).get(TaskViewModel::class.java)

        setObservers()
        setActions()
    }

    private fun taskClickListener(taskId: Long) {
        Log.i(TAG, "item id = $taskId")
        startActivity(DetailTaskActivity.start(this, taskId))
    }

    private fun setActions() {
        fab_add.setOnClickListener {
            startActivity(AddTaskActivity.start(this))
        }


    }

    private fun setObservers() {
        viewModel.allTasks.observe(this, Observer {
            adapter.submit(it)
        })
    }

    companion object {
        private val TAG = TaskActivity::class.java.name
    }
}
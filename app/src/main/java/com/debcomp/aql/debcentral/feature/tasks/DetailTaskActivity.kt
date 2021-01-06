package com.debcomp.aql.debcentral.feature.tasks

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.debcomp.aql.debcentral.DEBCentralApplication
import com.debcomp.aql.debcentral.feature.tasks.model.DetailTaskViewModel
import com.debcomp.aql.debcentral.infra.BaseActivity
import com.debcomp.aql.debcentral.infra.data.entity.TaskDTO

class DetailTaskActivity : BaseActivity() {

    private lateinit var viewModel: DetailTaskViewModel
    private lateinit var selectedTask: TaskDTO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel = ViewModelProvider(
            this,
            DetailTaskViewModel.DetailTaskViewModelFactory(DEBCentralApplication.instance)
        ).get(DetailTaskViewModel::class.java)

        setObservers()

        viewModel.start(intent.getLongExtra(EXTRA_TASK_ID, 0))
    }

    private fun setObservers() {
        viewModel.task.observe(this, Observer {

        })
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            else -> return super.onOptionsItemSelected(item)
        }
        return false
    }

    companion object {

        private const val EXTRA_TASK_ID = "EXTRA_TASK_ID_DETAIL"

        fun start(context: Context, taskId: Long): Intent {
            return Intent(context, AddTaskActivity::class.java)
                .apply { putExtra(EXTRA_TASK_ID, taskId) }
        }
    }
}
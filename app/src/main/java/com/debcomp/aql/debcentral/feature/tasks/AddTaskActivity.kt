package com.debcomp.aql.debcentral.feature.tasks

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.debcomp.aql.debcentral.DEBCentralApplication
import com.debcomp.aql.debcentral.R
import com.debcomp.aql.debcentral.feature.tasks.model.AddTaskViewModel
import com.debcomp.aql.debcentral.infra.BaseActivity
import com.debcomp.aql.debcentral.infra.data.entity.TaskDTO
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_add_task.*

class AddTaskActivity: BaseActivity() {

    private lateinit var viewModel: AddTaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_task_add)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel = ViewModelProvider(
            this,
            AddTaskViewModel.AddTaskViewModelFactory(DEBCentralApplication.instance)
        ).get(AddTaskViewModel::class.java)

        setActions()
    }

    private fun setActions() {
        btn_add_task.setOnClickListener {
            val name = edt_new_task.text.toString()
            if (name.isNotEmpty()) {
                viewModel.addTask(TaskDTO(name = name))
                finish()
            } else {
                Snackbar.make(edt_new_task, "É necessário dar um nome a tarefa", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            else -> return super.onOptionsItemSelected(item)
        }
        return false
    }

    companion object {
        fun start(context: Context): Intent{
            return Intent(context, AddTaskActivity::class.java)
        }
    }
}
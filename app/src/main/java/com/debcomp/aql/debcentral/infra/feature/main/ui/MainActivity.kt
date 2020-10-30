package com.debcomp.aql.debcentral.infra.feature.main.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.debcomp.aql.debcentral.DEBCentralApplication
import com.debcomp.aql.debcentral.R
import com.debcomp.aql.debcentral.infra.feature.main.model.TaskViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(
            this,
            TaskViewModel.TaskViewModelFactory(DEBCentralApplication.instance)
        ).get(TaskViewModel::class.java)
    }
}
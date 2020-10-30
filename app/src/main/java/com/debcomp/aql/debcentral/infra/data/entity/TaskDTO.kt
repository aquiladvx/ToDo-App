package com.debcomp.aql.debcentral.infra.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "task_table")
data class TaskDTO(
        @PrimaryKey(autoGenerate = true) val id: Int = 0,
        val name: String,
        val state: TaskState
)

enum class TaskState{
    TODO,
    DOING,
    DONE
}
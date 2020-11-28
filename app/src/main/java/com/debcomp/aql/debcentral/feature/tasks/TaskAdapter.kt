package com.debcomp.aql.debcentral.feature.tasks

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.debcomp.aql.debcentral.R
import com.debcomp.aql.debcentral.infra.data.entity.TaskDTO
import java.util.Collections.emptyList

class TaskAdapter internal constructor(
    context: Context,
    private val listener: (Long) -> Unit
): RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private val inflater = LayoutInflater.from(context)
    private var tasks = emptyList<TaskDTO>()

    inner class TaskViewHolder(itemView: View, private val listener: (Long) -> Unit):
            RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private val taskItemName: TextView = itemView.findViewById(R.id.txtItemTaskName)
        private val taskItemStatus: TextView = itemView.findViewById(R.id.txtItemTaskStatus)
        private lateinit var taskDTO: TaskDTO

        fun bind(data: TaskDTO) {
            taskDTO = data
            taskItemName.text = data.name
            taskItemStatus.text = data.state

            itemView.setOnClickListener {
                listener.invoke(taskDTO.id)
            }
        }
        
        override fun onClick(p0: View?) {
            listener.invoke(taskDTO.id)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView = inflater.inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(itemView, listener)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(tasks[position])
    }

    override fun getItemCount() = tasks.size

    internal fun submit(newTasks: List<TaskDTO>) {
        tasks = newTasks
        notifyDataSetChanged()
    }
}
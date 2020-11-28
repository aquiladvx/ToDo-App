package com.debcomp.aql.debcentral

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.debcomp.aql.debcentral.infra.base.AppDataBase
import com.debcomp.aql.debcentral.infra.data.local.TaskDAO
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.debcomp.aql.debcentral.infra.data.entity.TaskDTO


@RunWith(AndroidJUnit4::class)
class TaskDaoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    /*
        GIVEN

        WHEN

        THEN
     */

    private lateinit var taskDao: TaskDAO
    private lateinit var db: AppDataBase

    @Before
    fun createDb(){
        val context: Context = ApplicationProvider.getApplicationContext()

        db = Room.inMemoryDatabaseBuilder(context, AppDataBase::class.java)
            .allowMainThreadQueries()
            .build()
        taskDao = db.taskDao()
    }

    @After
    fun closeDb(){
        db.close()
    }

    @Test
    fun getAllTasks() {
        val allTasks = taskDao.getAllTasks().waitForValue()

        assertEquals(allTasks.size, 0)
    }

    @Test
    fun insertTask() {
        val task = TaskDTO(name = "testInsertTask")

        taskDao.insertTask(task)

        val res = taskDao.getAllTasks().waitForValue()

        assertEquals(res[0].name, task.name)
    }

    @Test
    fun getTaskById() {
        val task = TaskDTO(702, "testTaskById")

        taskDao.insertTask(task)
        val res = taskDao.getTaskById(702).waitForValue()

        assertEquals(task, res)
    }

    @Test
    fun deleteAllTasks() {
        val firstTask = TaskDTO(name = "testDeleteAllTasks")
        val secondTask = TaskDTO(name = "testDeleteAllTasks2")
        taskDao.insertTask(firstTask)
        taskDao.insertTask(secondTask)
        taskDao.deleteAllTasks()

        val res = taskDao.getAllTasks().waitForValue()

        assertEquals(res.size, 0)
    }

    @Test
    fun deleteTaskById() {
        val firstTask = TaskDTO(901, "testDeleteAllTasks")
        val secondTask = TaskDTO(902, "testDeleteAllTasks2")
        taskDao.insertTask(firstTask)
        taskDao.insertTask(secondTask)
        taskDao.deleteTaskById(901)

        val res = taskDao.getAllTasks().waitForValue()

        assertEquals(res[0].name, secondTask.name)
    }

    @Test
    fun updateTask() {
        val task = TaskDTO(name = "testUpdateTask")
        taskDao.insertTask(task)
        val allTasks = taskDao.getAllTasks().waitForValue()
        val updatedTask = allTasks[0].copy(name = "testNewNameUpdateTask")

        taskDao.updateTask(updatedTask)

        val refreshedTasks = taskDao.getAllTasks().waitForValue()
        assertEquals(refreshedTasks[0].name, updatedTask.name)
    }

}
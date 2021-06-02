package com.example.tasks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tasks.service.listener.ApiListener
import com.example.tasks.service.listener.ValidationListener
import com.example.tasks.service.repository.PriorityRepository
import com.example.tasks.service.repository.TaskRepository
import com.example.tasks.service.repository.models.PriorityModel
import com.example.tasks.service.repository.models.TaskModel

class TaskFormViewModel(application: Application) : AndroidViewModel(application) {

    private val mPriorityRepository = PriorityRepository(application)
    private val mTaskRepository = TaskRepository(application)

    private val mListPriority = MutableLiveData<List<PriorityModel>>()
    var priority: LiveData<List<PriorityModel>> = mListPriority

    private val mValidation = MutableLiveData<ValidationListener>()
    val validation: LiveData<ValidationListener> = mValidation

    fun listPriorities() {
        mListPriority.value = mPriorityRepository.list()
    }

    fun save(taskModel: TaskModel) {
        mTaskRepository.create(taskModel, object : ApiListener<Boolean> {
            override fun onSuccess(model: Boolean) {
                mValidation.value = ValidationListener()
            }

            override fun onFailure(msg: String) {
                mValidation.value = ValidationListener(msg)
            }

        })
    }


}
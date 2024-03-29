package com.example.tasks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tasks.service.repository.models.HeaderModel
import com.example.tasks.service.constants.TaskConstants
import com.example.tasks.service.listener.ApiListener
import com.example.tasks.service.listener.ValidationListener
import com.example.tasks.service.repository.PersonRepository
import com.example.tasks.service.repository.local.SecurityPreferences

class RegisterViewModel(application: Application) : AndroidViewModel(application) {

    private val mPersonRepository = PersonRepository(application)
    private val mSharedPreferences = SecurityPreferences(application)

    private val mCreate = MutableLiveData<ValidationListener>()
    var create: LiveData<ValidationListener> = mCreate

    fun create(name: String, email: String, password: String) {
        mPersonRepository.create(name, email, password, object : ApiListener<HeaderModel>{
            override fun onSuccess(model: HeaderModel) {
                mSharedPreferences.store(TaskConstants.SHARED.PERSON_NAME, model.name)
                mSharedPreferences.store(TaskConstants.SHARED.TOKEN_KEY, model.personKey)
                mSharedPreferences.store(TaskConstants.SHARED.PERSON_KEY, model.token)

                mCreate.value = ValidationListener()
            }

            override fun onFailure(msg: String) {
                mCreate.value = ValidationListener(msg)
            }

        })
    }

}
package com.example.tasks.viewmodel

import android.app.Application
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tasks.service.HeaderModel
import com.example.tasks.service.constants.TaskConstants
import com.example.tasks.service.listener.ListenerApi
import com.example.tasks.service.listener.ValidationListener
import com.example.tasks.service.repository.PersonRepository
import com.example.tasks.service.repository.local.SecurityPreferences

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val mPersonRepository = PersonRepository(application)
    private val mSharedPreferences = SecurityPreferences(application)

    private val mLogin = MutableLiveData<ValidationListener>()
    var login: LiveData<ValidationListener> = mLogin

    /**
     * Faz login usando API
     */
    fun doLogin(email: String, password: String) {
        mPersonRepository.login(email, password,  object: ListenerApi{
            override fun onSuccess(headerModel: HeaderModel) {

                mSharedPreferences.store(TaskConstants.SHARED.TOKEN_KEY, headerModel.token)
                mSharedPreferences.store(TaskConstants.SHARED.PERSON_KEY, headerModel.personKey)
                mSharedPreferences.store(TaskConstants.SHARED.PERSON_NAME, headerModel.name)

                mLogin.value = ValidationListener()
            }

            override fun onFailure(msg: String) {
                mLogin.value = ValidationListener(msg)
            }

        })
    }

    /**
     * Verifica se usuário está logado
     */
    fun verifyLoggedUser() {
    }

}
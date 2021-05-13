package com.example.tasks.service.repository

import android.content.Context
import com.example.tasks.R
import com.example.tasks.service.HeaderModel
import com.example.tasks.service.constants.TaskConstants
import com.example.tasks.service.listener.ListenerApi
import com.example.tasks.service.repository.remote.PersonService
import com.example.tasks.service.repository.remote.RetrofitClient
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PersonRepository(val context: Context) {

    private val mRemote = RetrofitClient.createService(PersonService::class.java)

    fun login(email: String, password: String, listenerApi: ListenerApi) {
        val call: Call<HeaderModel> = mRemote.login(email, password)
        call.enqueue(object : Callback<HeaderModel> {
            override fun onResponse(call: Call<HeaderModel>, response: Response<HeaderModel>) {

                if (response.code() != TaskConstants.HTTP.SUCCESS) {
                    val validation =
                        Gson().fromJson(response.errorBody()!!.string(), String::class.java)
                    listenerApi.onFailure(validation)

                } else {
                    response.body()?.let { listenerApi.onSuccess(it) }
                }

            }

            override fun onFailure(call: Call<HeaderModel>, t: Throwable) {
                listenerApi.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
            }

        })
    }
}
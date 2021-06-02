package com.example.tasks.service.repository

import android.content.Context
import android.util.Log
import com.example.tasks.service.constants.TaskConstants
import com.example.tasks.service.repository.local.TaskDatabase
import com.example.tasks.service.repository.models.PriorityModel
import com.example.tasks.service.repository.remote.PriorityService
import com.example.tasks.service.repository.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PriorityRepository(context: Context) {

    private val mRemote = RetrofitClient.createService(PriorityService::class.java)
    private val mPriorityDatabase = TaskDatabase.getDatabase(context).priorityDAO()

    fun all(){
        val call: Call<List<PriorityModel>> = mRemote.all()
        call.enqueue(object : Callback<List<PriorityModel>>{
            override fun onResponse(
                call: Call<List<PriorityModel>>,
                response: Response<List<PriorityModel>>
            ) {
                if(response.code() == TaskConstants.HTTP.SUCCESS){
                    mPriorityDatabase.clear()
                    response.body()?.let {
                        mPriorityDatabase.save(it)
                        Log.i("RESPOSTA", "Resposta $it")
                    }
                }
            }

            override fun onFailure(call: Call<List<PriorityModel>>, t: Throwable) {

            }

        })
    }

    fun list() = mPriorityDatabase.listPriority()
}
package com.example.tasks.service.repository.remote

import com.example.tasks.service.repository.models.TaskModel
import retrofit2.Call
import retrofit2.http.*

interface TaskService {

    @GET("Task")
    fun all(): Call<List<TaskModel>>

    @GET("Task/Next7Days")
    fun nextWeek(): Call<List<TaskModel>>

    @GET("Task/Overdue")
    fun overdue(): Call<List<TaskModel>>

    @GET("Task/{id}")
    fun getTask(@Path(value = "id", encoded = true)id: Int): Call<TaskModel>

    @POST("Task")
    @FormUrlEncoded
    fun create(
        @Field("PriorityId") priority: Int,
        @Field("Description") description: String,
        @Field("DueDate") dueDate: String,
        @Field("Complete") complete: Boolean
    ):Call<Boolean>

    @HTTP(method = "PUT", path = "Task", hasBody = true)
    @FormUrlEncoded
    fun update(
        @Field("Id") id: Int,
        @Field("PriorityId") priority: Int,
        @Field("Description") description: String,
        @Field("DueDate") dueDate: String,
        @Field("Complete") complete: Boolean
    ):Call<Boolean>

    @HTTP(method = "PUT", path = "Task/Complete", hasBody = true)
    @FormUrlEncoded
    fun complete(
        @Field("Id") id: Int
    ):Call<Boolean>

    @HTTP(method = "PUT", path = "Task/Undo", hasBody = true)
    @FormUrlEncoded
    fun undo(
        @Field("Id") id: Int
    ):Call<Boolean>

    @HTTP(method = "DELETE", path = "Task/Undo", hasBody = true)
    @FormUrlEncoded
    fun delete(
        @Field("Id") id: Int
    ):Call<Boolean>
}
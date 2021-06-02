package com.example.tasks.service.repository.models

import com.google.gson.annotations.SerializedName

class TaskModel {

    @SerializedName("Name")
    var id: Int = 0

    @SerializedName("Priority")
    var priorityId: Int = 0

    @SerializedName("Description")
    var description: String = ""

    @SerializedName("DueData")
    var dueData: String = ""

    @SerializedName("Complete")
    var complete: Boolean = false
}
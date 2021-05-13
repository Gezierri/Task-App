package com.example.tasks.service.listener

import com.example.tasks.service.HeaderModel

interface ListenerApi {

    fun onSuccess(model: HeaderModel)
    fun onFailure(msg: String)
}
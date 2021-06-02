package com.example.tasks.service.listener

import com.example.tasks.service.repository.models.HeaderModel

interface ApiListener<T> {

    fun onSuccess(model: T)
    fun onFailure(msg: String)
}
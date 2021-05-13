package com.example.tasks.service.listener

class ValidationListener(msg: String = "") {

    private var success: Boolean = true
    private var failure: String = ""

    init {
        if (msg.isNotEmpty()){
            success = false
            failure = msg
        }
    }

    fun getSuccess() = success
    fun getFailure() = failure
}
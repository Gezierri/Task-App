package com.example.tasks.service.repository.models

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PriorityDao {

    @Insert
    fun save(list: List<PriorityModel>)

    @Query("SELECT * FROM priority")
    fun listPriority(): List<PriorityModel>

    @Query("DELETE FROM priority")
    fun clear()

}
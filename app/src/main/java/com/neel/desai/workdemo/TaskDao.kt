package com.neel.desai.workdemo

import androidx.lifecycle.LiveData
import androidx.room.*
import com.neel.desai.workdemo.model.Results

@Dao
open interface TaskDao {

    @Query("SELECT * FROM Results ")
    fun getAll(): LiveData<List<Results>>

    @Query("SELECT * FROM Results WHERE  PersonId='1'")
    fun getuser(): LiveData<List<Results>>
    @Insert
    fun insert(task: Results)

    @Delete
    public fun delete(task: Results)

    @Update
    public fun update(task: Results?)
}
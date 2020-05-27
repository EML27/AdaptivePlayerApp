package com.itis.adaptiveplayerapp.bl.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.itis.adaptiveplayerapp.bl.room.entity.StateEntity

@Dao
interface StateDao {
    @Query("SELECT * FROM state")
    fun getAll(): LiveData<List<StateEntity>>

    @Query("SELECT * FROM state WHERE state.stateId= :id")
    fun getStateById(id: Long): StateEntity

    @Query("SELECT * FROM state WHERE weather=:weather AND time=:time AND occupation=:occupation")
    fun getStateByAttributes(weather: String, time: String, occupation: String): StateEntity

    @Insert
    fun insert(state: StateEntity)

    @Insert
    fun delete(state: StateEntity)
}
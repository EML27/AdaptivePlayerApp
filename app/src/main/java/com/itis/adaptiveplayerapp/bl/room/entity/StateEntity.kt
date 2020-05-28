package com.itis.adaptiveplayerapp.bl.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "state")
data class StateEntity(
    @PrimaryKey(autoGenerate = true)
    var stateId: Long,
    var weather: String,
    var time: String,
    var occupation: String
)
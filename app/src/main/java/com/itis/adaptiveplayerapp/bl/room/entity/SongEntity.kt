package com.itis.adaptiveplayerapp.bl.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "song")
data class SongEntity(
    @PrimaryKey(autoGenerate = true)
    var songId: Long,
    var url: String
)
package com.itis.adaptiveplayerapp.bl.room.entity.relations

import androidx.room.Entity

@Entity(primaryKeys = ["stateId", "songId"], tableName = "song_state")
data class SongStateCrossRef(
    var stateId: Long,
    var songId: Long
)

package com.itis.adaptiveplayerapp.bl.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.itis.adaptiveplayerapp.bl.room.entity.relations.SongStateCrossRef

@Dao
interface SongStateRefDao {
    @Query("SELECT * FROM song_state")
    fun getAll()

    @Query("SELECT * FROM SONG_STATE WHERE stateId =:stateId")
    fun getSongsIdsByStateId(stateId: Long): List<Long>

    @Query("SELECT * FROM SONG_STATE WHERE songId =:songId")
    fun getStatesIdsBySongId(songId: Long): List<Long>

    @Insert
    fun insert(songStateRef: SongStateCrossRef)

    @Delete
    fun delete(songStateRef: SongStateCrossRef)
}
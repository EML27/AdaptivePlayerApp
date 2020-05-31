package com.itis.adaptiveplayerapp.bl.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.itis.adaptiveplayerapp.bl.room.entity.relations.SongStateCrossRef

@Dao
interface SongStateRefDao {
    @Query("SELECT * FROM song_state")
    fun getAll(): List<SongStateCrossRef>

    @Query("SELECT song_state.songId FROM SONG_STATE WHERE stateId =:stateId")
    fun getSongsIdsByStateId(stateId: Long): List<Long>

    @Query("SELECT song_state.stateId FROM SONG_STATE WHERE songId =:songId")
    fun getStatesIdsBySongId(songId: Long): List<Long>

    @Query("SELECT * FROM SONG_STATE WHERE stateId = :stateId AND songId=:songId")
    fun getByIds(songId: Long, stateId: Long): SongStateCrossRef?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(songStateRef: SongStateCrossRef)

    @Delete
    fun delete(songStateRef: SongStateCrossRef)
}
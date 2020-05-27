package com.itis.adaptiveplayerapp.bl.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.itis.adaptiveplayerapp.bl.room.entity.SongEntity
import com.itis.adaptiveplayerapp.bl.room.entity.StateEntity

@Dao
interface SongDao {

    @Query("SELECT * FROM song")
    fun getAll(): List<SongEntity>

    @Query("SELECT * FROM song WHERE songId =:id")
    fun getSongById(id: Long): SongEntity

    @Query("SELECT songId,url FROM song INNER JOIN song_state ON song.songId=song_state.songId WHERE stateId=:stateEntityId")
    fun getSongsByState(stateEntityId: Long): List<SongEntity>

    @Insert
    fun insert(song: SongEntity)

    @Delete
    fun delete(song: SongEntity)
}
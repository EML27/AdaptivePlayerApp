package com.itis.adaptiveplayerapp.bl.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.itis.adaptiveplayerapp.bl.room.dao.SongDao
import com.itis.adaptiveplayerapp.bl.room.dao.SongStateRefDao
import com.itis.adaptiveplayerapp.bl.room.dao.StateDao
import com.itis.adaptiveplayerapp.bl.room.entity.SongEntity
import com.itis.adaptiveplayerapp.bl.room.entity.StateEntity
import com.itis.adaptiveplayerapp.bl.room.entity.relations.SongStateCrossRef

@Database(entities = [SongEntity::class, StateEntity::class, SongStateCrossRef::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun songDao(): SongDao
    abstract fun stateDao(): StateDao
    abstract fun songStateDao(): SongStateRefDao
}
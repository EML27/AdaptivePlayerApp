package com.itis.adaptiveplayerapp.bl

import com.itis.adaptiveplayerapp.App
import com.itis.adaptiveplayerapp.bl.dto.StateDto
import com.itis.adaptiveplayerapp.bl.gps.Occupation
import com.itis.adaptiveplayerapp.bl.room.database.AppDatabase
import com.itis.adaptiveplayerapp.bl.room.entity.SongEntity
import com.itis.adaptiveplayerapp.bl.room.entity.StateEntity
import com.itis.adaptiveplayerapp.bl.room.entity.relations.SongStateCrossRef
import com.itis.adaptiveplayerapp.bl.time.TimeOfDay
import com.itis.adaptiveplayerapp.di.component.DaggerInjectForMusicRecommenderComponent
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MusicRecommender @Inject constructor() {


    private var db = App.getDb()

    private suspend fun getPlaylistByStateFromDB(state: StateDto): List<SongEntity> {
        var stateDB = db.stateDao()
            .getStateByAttributes(
                state.weather ?: "",
                state.time ?: "",
                state.occupation ?: ""
            )
        if (stateDB == null) {
            db.stateDao().insert(
                StateEntity(
                    0,
                    state.weather ?: "",
                    state.time ?: "",
                    state.occupation ?: ""
                )
            )
            stateDB = db.stateDao()
                .getStateByAttributes(
                    state.weather ?: "",
                    state.time ?: "",
                    state.occupation ?: ""
                )
        }

        val res = stateDB?.stateId?.let { db.songDao().getSongsByState(it) }
        return res?.value ?: ArrayList()
    }

    suspend fun getPlaylistByState(state: StateDto): List<String> {
        val res = getPlaylistByStateFromDB(state)
        return if (res.isNotEmpty()) {
            res.map { song -> song.url }
        } else {
            getDefaultPlaylist(state)
        }
    }

    private fun getDefaultPlaylist(state: StateDto): List<String> {
        return when {
            state.occupation == Occupation.JOG.name -> arrayListOf("spotify:playlist:37i9dQZF1DXadOVCgGhS7j")
            state.occupation == Occupation.WALK.name -> arrayListOf("spotify:playlist:37i9dQZF1DX1tyCD9QhIWF")
            state.occupation == Occupation.CALMTRANSPORT.name -> arrayListOf("spotify:playlist:37i9dQZF1DWWMOmoXKqHTD")
            state.time == TimeOfDay.MORNING.name -> arrayListOf("spotify:playlist:37i9dQZF1DWT3gM3xdPT0c")
            state.weather == "Rain" -> arrayListOf("spotify:playlist:37i9dQZF1DX1s9knjP51Oa")
            state.occupation == Occupation.CALM.name -> arrayListOf("spotify:playlist:37i9dQZF1DWWQRwui0ExPn")
            else -> arrayListOf("spotify:playlist:37i9dQZF1DX1gRalH1mWrP")
        }
    }

    fun learn(state: StateDto, songUrl: String) {
        var song = db.songDao().getSongByUrl(songUrl)
        if (song == null) {
            song = SongEntity(0, songUrl)
            db.songDao().insert(song)
            song = db.songDao().getSongByUrl(songUrl)
        }
        var stateEnt = db.stateDao()
            .getStateByAttributes(
                state.weather ?: "",
                state.time ?: "",
                state.occupation ?: ""
            )
        if (stateEnt == null) {
            stateEnt = StateEntity(
                0,
                state.weather ?: "",
                state.time ?: "",
                state.occupation ?: ""
            )
            db.stateDao().insert(stateEnt)
            stateEnt = db.stateDao().getStateByAttributes(
                state.weather ?: "",
                state.time ?: "",
                state.occupation ?: ""
            )
        }
        db.songStateDao().insert(SongStateCrossRef(stateEnt?.stateId ?: 0, song?.songId ?: 0))
    }


}